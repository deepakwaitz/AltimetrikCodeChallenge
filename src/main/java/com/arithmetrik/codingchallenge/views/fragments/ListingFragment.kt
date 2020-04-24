package com.arithmetrik.codingchallenge.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arithmetrik.codingchallenge.AltimetrikViewModel
import com.arithmetrik.codingchallenge.R
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import com.arithmetrik.codingchallenge.views.ListItemAdapter
import java.util.*

class ListingFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: AltimetrikViewModel
    private var listAdapter: ListItemAdapter? = null
    private var dataList: ArrayList<DataModel> = ArrayList<DataModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AltimetrikViewModel::class.java)
        viewModel.getData()?.observe(this, Observer {
            Log.d("ListingFragment size - ", it.size.toString())
            dataList.addAll(it)
            listAdapter?.notifyDataSetChanged()
        })
        setupRecyclerView(dataList)
    }

    private fun setupRecyclerView(it: List<DataModel>) {
        if (listAdapter == null) {
            listAdapter = ListItemAdapter(context, it)
            recyclerView?.layoutManager = LinearLayoutManager(this.context)
            recyclerView?.adapter = listAdapter
        } else
            listAdapter?.notifyDataSetChanged()
    }
}