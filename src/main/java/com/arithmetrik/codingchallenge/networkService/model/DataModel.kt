package com.arithmetrik.codingchallenge.networkService.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel {
    @SerializedName("s.no")
    @Expose
    var serialNo: Int? = null

    @SerializedName("amt.pledged")
    @Expose
    var pledged: Int? = null

    @SerializedName("blurb")
    @Expose
    var blurb: String? = null

    @SerializedName("by")
    @Expose
    var author: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("end.time")
    @Expose
    var endTime: String? = null

    @SerializedName("location")
    @Expose
    var location: String? = null

    @SerializedName("percentage.funded")
    @Expose
    var percentageFunded: Int? = null

    @SerializedName("num.backers")
    @Expose
    var backers: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}