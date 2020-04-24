package com.arithmetrik.codingchallenge.networkService.model

import Constants.Companion.TABLE_DATA
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_DATA)
data class DataModel(
    @SerializedName("s.no")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "s_no")
    @Expose
    var serialNo: Int? = null,

    @SerializedName("amt.pledged")
    @ColumnInfo(name = "amt_pledged")
    @Expose
    var pledged: Int? = null,

    @SerializedName("blurb")
    @ColumnInfo(name = "blurb")
    @Expose
    var blurb: String? = null,

    @SerializedName("by")
    @ColumnInfo(name = "by")
    @Expose
    var author: String? = null,

    @SerializedName("country")
    @ColumnInfo(name = "country")
    @Expose
    var country: String? = null,

    @SerializedName("currency")
    @ColumnInfo(name = "currency")
    @Expose
    var currency: String? = null,

    @SerializedName("end.time")
    @ColumnInfo(name = "end_time")
    @Expose
    var endTime: String? = null,

    @SerializedName("location")
    @ColumnInfo(name = "location")
    @Expose
    var location: String? = null,

    @SerializedName("percentage.funded")
    @ColumnInfo(name = "percentage_funded")
    @Expose
    var percentageFunded: Int? = null,

    @SerializedName("num.backers")
    @ColumnInfo(name = "num_backers")
    @Expose
    var backers: String? = null,

    @SerializedName("state")
    @ColumnInfo(name = "state")
    @Expose
    var state: String? = null,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    @Expose
    var title: String? = null,

    @SerializedName("type")
    @ColumnInfo(name = "type")
    @Expose
    var type: String? = null,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    @Expose
    var url: String? = null
) : Parcelable