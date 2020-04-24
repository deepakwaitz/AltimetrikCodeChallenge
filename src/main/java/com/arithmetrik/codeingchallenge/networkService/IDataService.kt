package com.arithmetrik.codeingchallenge.networkService

import Constants.Companion.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8
import com.arithmetrik.codeingchallenge.networkService.model.ResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface IDataService {

    @Headers(CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("http://starlord.hackerearth.com/kickstarter")
    fun getData(): Flowable<ResponseModel>
}