package com.arithmetrik.codingchallenge.views

import Constants.Companion.EXTRA_DATA
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arithmetrik.codingchallenge.R
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import com.arithmetrik.codingchallenge.views.fragments.DetailFragment

class ListItemAdapter(
    private val context: Context?,
    private val dataList: List<DataModel>,
    private val fragmentCallbacks: FragmentCallBack?
) : RecyclerView.Adapter<ItemViewHolder>(),
    View.OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
        //init tag for the handling the clicks
        holder.itemView.tag=position
        holder.itemView.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        val itemPosition: Int = v?.tag as Int
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_DATA, dataList[itemPosition])

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        fragmentCallbacks?.addFragment(detailFragment,true)
    }
}