package com.arithmetrik.codingchallenge.utils;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom Scroll listener for RecyclerView.
 * Based on implementation https://gist.github.com/ssinss/e06f12ef66c51252563e
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static final String TAG = "EndlessScrollListener";
    private int mFirstVisibleItem;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private RecyclerViewPositionHelper mRecyclerViewHelper;
    private boolean mLoading = true; // True if we are still waiting for the last set of data to load.
    private int mCurrentPage = 0;
    private int mVisibleThreshold = 5;//visible offset to call the next API call once reaching the end + Threshold

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
            mVisibleItemCount = recyclerView.getChildCount();
            mTotalItemCount = mRecyclerViewHelper.getItemCount();
            mFirstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();

            // The minimum amount of items to have below your current scroll position before mLoading more.
            if ((mTotalItemCount - mVisibleItemCount)
                    <= (mFirstVisibleItem + mVisibleThreshold)) {
                // End has been reached
                // Do something
                mCurrentPage++;

                onLoadMore(mCurrentPage);

                mLoading = true;
            }
        }
    }

    /**
     * used to set for the API is completed and allow for loading
     */
    public void setLoadingCompleted() {
        mLoading = false;
    }

    // Call whenever doing swipe refresh
    public void resetState() {
        this.mCurrentPage = 0;
        this.mLoading = true;
    }

    //Start mLoading
    public abstract void onLoadMore(int currentPage);
}

