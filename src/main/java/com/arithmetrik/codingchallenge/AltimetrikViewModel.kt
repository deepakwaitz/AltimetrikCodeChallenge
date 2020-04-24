package com.arithmetrik.codingchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arithmetrik.codingchallenge.networkService.model.DataModel

class AltimetrikViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<List<DataModel>>? = null

    init {
        mutableLiveData = AltimetrikRepository.getDataFromAPI()
    }

    fun getData(): MutableLiveData<List<DataModel>>? {
        return mutableLiveData
    }
}