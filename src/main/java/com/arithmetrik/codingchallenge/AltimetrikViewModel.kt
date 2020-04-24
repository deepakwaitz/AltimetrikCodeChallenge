package com.arithmetrik.codingchallenge

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arithmetrik.codingchallenge.networkService.model.DataModel

class AltimetrikViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<List<DataModel>>? = null

    /*init {
        mutableLiveData = AltimetrikRepository.getData()
    }*/

    fun getData(context: Context?): MutableLiveData<List<DataModel>>? {
        return AltimetrikRepository.getData(context)
    }
}