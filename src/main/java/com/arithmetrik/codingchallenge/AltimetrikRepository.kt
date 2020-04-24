package com.arithmetrik.codingchallenge

import RetrofitApiClient
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arithmetrik.codingchallenge.networkService.IDataService
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AltimetrikRepository {
    fun getDataFromAPI(): MutableLiveData<List<DataModel>> {
        val retrofitService: IDataService =
            RetrofitApiClient.getRetrofitApiClient()
                .create(IDataService::class.java)

        val newsData: MutableLiveData<List<DataModel>> = MutableLiveData<List<DataModel>>()

        retrofitService.getData().enqueue(object : Callback<List<DataModel>> {
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                newsData.value = null
                Log.d("Repository- onFailure", t.message)
            }

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                Log.d("Repository- onResponse", "")
                newsData.value = response.body()
            }
        })

        return newsData
    }
}