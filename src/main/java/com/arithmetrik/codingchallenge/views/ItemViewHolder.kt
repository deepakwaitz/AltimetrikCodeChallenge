package com.arithmetrik.codingchallenge.views

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arithmetrik.codingchallenge.R
import com.arithmetrik.codingchallenge.networkService.model.DataModel

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mContext: Context? = null
    private var titleTV: TextView? = null
    private var authorTV: TextView? = null
    private var percentageTV: TextView? = null

    init {
        mContext = itemView.context
        titleTV = itemView.findViewById(R.id.title)
        authorTV = itemView.findViewById(R.id.author)
        percentageTV = itemView.findViewById(R.id.percentage)
    }


    fun bind(element: DataModel) {
        titleTV?.text = element.title
        authorTV?.text = mContext?.getString(R.string.by_placeholder).toString() + " " + element.author
        percentageTV?.text = mContext?.getString(R.string.funded_placeholder) + element.percentageFunded.toString()
    }
}