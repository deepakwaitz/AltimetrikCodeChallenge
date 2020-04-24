package com.arithmetrik.codingchallenge

import Constants.Companion.ITEM_LIMIT
import RetrofitApiClient
import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.arithmetrik.codingchallenge.db.AppDataBase
import com.arithmetrik.codingchallenge.networkService.IDataService
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AltimetrikRepository {
    private var appDatabase: AppDataBase? = null
    val newsData: MutableLiveData<List<DataModel>> = MutableLiveData<List<DataModel>>()

    fun getData(context: Context?, offSet: Int, searchTerm: String): MutableLiveData<List<DataModel>> {
        if (appDatabase == null)
            appDatabase = AppDataBase.getAppDataBaseInstance(context)

        if (appDatabase?.dataDao()?.getCount() != 0) {
            if (!TextUtils.isEmpty(searchTerm))
                newsData.value = appDatabase?.dataDao()?.getSearchedData(offSet, ITEM_LIMIT, "%" + searchTerm + "%")
            else
                newsData.value = appDatabase?.dataDao()?.getLimitedData(offSet, ITEM_LIMIT)
            return newsData
        } else
            return getDataFromAPI(offSet)
    }

    private fun getDataFromAPI(offSet: Int): MutableLiveData<List<DataModel>> {
        val retrofitService: IDataService =
            RetrofitApiClient.getRetrofitApiClient()
                .create(IDataService::class.java)

        retrofitService.getData().enqueue(object : Callback<List<DataModel>> {
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                newsData.value = null
            }

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                response.body()?.let { appDatabase?.dataDao()?.insertData(it) }
                newsData.value = appDatabase?.dataDao()?.getLimitedData(offSet, ITEM_LIMIT)
            }
        })

        return newsData
    }
}