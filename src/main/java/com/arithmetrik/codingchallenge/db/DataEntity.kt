package com.arithmetrik.codingchallenge.db

import Constants.Companion.TABLE_DATA
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_DATA)
data class DataEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "s.no") var serialNo: String = "",
    @ColumnInfo(name = "blurb") var blurb: String = "",
    @ColumnInfo(name = "by") var author: String = "",
    @ColumnInfo(name = "country") var country: String = "",
    @ColumnInfo(name = "currency") var currency: String = "",
    @ColumnInfo(name = "end.time") var endTime: String = "",
    @ColumnInfo(name = "location") var location: String = "",
    @ColumnInfo(name = "percentage.funded") var percentageFunded: String = "",
    @ColumnInfo(name = "backers") var backers: String = "",
    @ColumnInfo(name = "state") var state: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "type") var type: String = "",
    @ColumnInfo(name = "url") var url: String = ""
)