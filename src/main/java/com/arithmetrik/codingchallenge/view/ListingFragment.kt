package com.arithmetrik.codingchallenge.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.arithmetrik.codingchallenge.AltimetrikViewModel
import com.arithmetrik.codingchallenge.R

class ListingFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: AltimetrikViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)


        viewModel = ViewModelProviders.of(this).get(AltimetrikViewModel::class.java)
        viewModel.getData()?.observe(this, Observer {
            Log.d("ListingFragment size - ", it.size.toString())
        })
        return view
    }

}