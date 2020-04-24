package com.arithmetrik.codingchallenge

import RetrofitApiClient
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arithmetrik.codingchallenge.db.AppDataBase
import com.arithmetrik.codingchallenge.networkService.IDataService
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AltimetrikRepository {
    private var appDatabase: AppDataBase? = null

    fun search(context: Context?, searchTearm: String): MutableLiveData<List<DataModel>> {
        val searchData: MutableLiveData<List<DataModel>> = MutableLiveData<List<DataModel>>()
        if (appDatabase == null)
            appDatabase = AppDataBase.getAppDataBaseInstance(context)

        if (appDatabase?.dataDao()?.getCount() != 0) {
            searchData.value = appDatabase?.dataDao()?.search("%"+searchTearm+"%")
            return searchData
        }
        return searchData
    }


    fun getData(context: Context?): MutableLiveData<List<DataModel>> {
        val newsData: MutableLiveData<List<DataModel>> = MutableLiveData<List<DataModel>>()
        if (appDatabase == null)
            appDatabase = AppDataBase.getAppDataBaseInstance(context)


        if (appDatabase?.dataDao()?.getCount() != 0) {
            newsData.value = appDatabase?.dataDao()?.getAllData()
            return newsData
        } else
            return getDataFromAPI(newsData)
    }

    private fun getDataFromAPI(newsData: MutableLiveData<List<DataModel>>): MutableLiveData<List<DataModel>> {
        val retrofitService: IDataService =
            RetrofitApiClient.getRetrofitApiClient()
                .create(IDataService::class.java)

        retrofitService.getData().enqueue(object : Callback<List<DataModel>> {
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                newsData.value = null
                Log.d("Repository- onFailure", t.message)
            }

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                Log.d("Repository- onResponse", "")
                //newsData.value = response.body()
                response.body()?.let { appDatabase?.dataDao()?.insertData(it) }
                newsData.value = appDatabase?.dataDao()?.getAllData()
            }
        })

        return newsData
    }
}