package com.arithmetrik.codingchallenge.networkService

import Constants.Companion.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8
import com.arithmetrik.codingchallenge.networkService.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface IDataService {

    @Headers(CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("http://starlord.hackerearth.com/kickstarter")
    fun getData(): Call<List<DataModel>>
}