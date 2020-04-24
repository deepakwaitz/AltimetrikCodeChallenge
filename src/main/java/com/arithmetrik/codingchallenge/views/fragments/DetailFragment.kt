package com.arithmetrik.codingchallenge.views.fragments

import Constants.Companion.EXTRA_DATA
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import com.arithmetrik.codingchallenge.R
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import kotlinx.android.synthetic.main.detail_screen_layout.*

class DetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.detail_screen_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateValues()
    }

    private fun populateValues() {
        val data: DataModel? = arguments?.getParcelable<DataModel>(EXTRA_DATA)
        detail_title.text = data?.title
        detail_blurb.text = setSpannableStringBuilder(getString(R.string.blurb_placeholder), data?.blurb)
        detail_percentage.text = setSpannableStringBuilder(getString(R.string.funded_placeholder), data?.percentageFunded.toString())
        detail_author.text = setSpannableStringBuilder(getString(R.string.by_placeholder), data?.author)
        detail_country.text = setSpannableStringBuilder(getString(R.string.country_placeholder), data?.country)
        detail_state.text = setSpannableStringBuilder(getString(R.string.state_placeholder), data?.state)
        detail_location.text = setSpannableStringBuilder(getString(R.string.location_placeholder), data?.location)
        detail_backers.text = setSpannableStringBuilder(getString(R.string.backers_placeholder), data?.backers)
        detail_type.text = setSpannableStringBuilder(getString(R.string.type_placeholder), data?.type)
    }

    private fun setSpannableStringBuilder(placeholder: String, value: String?): SpannableStringBuilder {
        return SpannableStringBuilder()
            .bold { append(placeholder) }
            .append(value)
    }

}