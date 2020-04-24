package com.arithmetrik.codingchallenge.views.fragments

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arithmetrik.codingchallenge.AltimetrikViewModel
import com.arithmetrik.codingchallenge.R
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import com.arithmetrik.codingchallenge.views.FragmentCallBack
import com.arithmetrik.codingchallenge.views.ListItemAdapter
import java.util.*

class ListingFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var searchET: EditText? = null
    private var errorMsgTV: TextView? = null
    private lateinit var viewModel: AltimetrikViewModel
    private var listAdapter: ListItemAdapter? = null
    private var dataList: ArrayList<DataModel> = ArrayList<DataModel>()
    var fragmentCallbacks: FragmentCallBack? = null
    private var searchTerm: String = ""

    /*
       * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
       * Use onAttachToContext instead
       */
    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    /*
   * Called when the fragment attaches to the context
   */
    private fun onAttachToContext(context: Context) {
        if (context is FragmentCallBack) {
            fragmentCallbacks = context
        } else {
            throw RuntimeException("$context must implement FragmentCallback methods")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        searchET = view.findViewById(R.id.search_edit_text)
        errorMsgTV = view.findViewById(R.id.error_message)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchET?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchET?.windowToken, 0)
                searchTerm = searchET?.text?.trim().toString()
                if (!TextUtils.isEmpty(searchTerm)) {
                    viewModel.getSearchData(context, searchTerm)?.observe(this, Observer {
                        if (it != null && it.size > 0) {
                            dataList.clear()
                            dataList.addAll(it)
                            listAdapter?.notifyDataSetChanged()
                            recyclerView?.visibility = View.VISIBLE
                            errorMsgTV?.visibility = View.GONE
                        } else {
                            recyclerView?.visibility = View.GONE
                            errorMsgTV?.visibility = View.VISIBLE
                        }
                    })
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }


        viewModel = ViewModelProviders.of(this).get(AltimetrikViewModel::class.java)
        viewModel.getData(context)?.observe(this, Observer {
            if (it != null && it.size > 0) {
                dataList.clear()
                dataList.addAll(it)
                listAdapter?.notifyDataSetChanged()
                recyclerView?.visibility = View.VISIBLE
                errorMsgTV?.visibility = View.GONE
            } else {
                recyclerView?.visibility = View.GONE
                errorMsgTV?.visibility = View.VISIBLE
            }
        })
        setupRecyclerView(dataList)
    }

    private fun setupRecyclerView(it: List<DataModel>) {
        if (listAdapter == null) {
            listAdapter = ListItemAdapter(context, it, fragmentCallbacks)
            recyclerView?.layoutManager = LinearLayoutManager(this.context)
            recyclerView?.adapter = listAdapter
        } else
            listAdapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCallbacks = null
    }
}