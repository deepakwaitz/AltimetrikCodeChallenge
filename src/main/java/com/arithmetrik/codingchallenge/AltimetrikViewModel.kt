package com.arithmetrik.codingchallenge

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arithmetrik.codingchallenge.networkService.model.DataModel

class AltimetrikViewModel : ViewModel() {
    fun getData(context: Context?, offSet: Int, searchTerm: String): MutableLiveData<List<DataModel>>? {
        return AltimetrikRepository.getData(context, offSet, searchTerm)
    }
}