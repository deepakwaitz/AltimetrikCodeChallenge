package com.arithmetrik.codingchallenge

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arithmetrik.codingchallenge.networkService.model.DataModel

class AltimetrikViewModel : ViewModel() {
    fun getData(context: Context?): MutableLiveData<List<DataModel>>? {
        return AltimetrikRepository.getData(context)
    }

    fun getSearchData(context: Context?, searchTerm: String): MutableLiveData<List<DataModel>>? {
        return AltimetrikRepository.search(context, searchTerm)
    }
}