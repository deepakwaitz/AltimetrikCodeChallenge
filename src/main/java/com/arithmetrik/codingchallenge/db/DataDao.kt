package com.arithmetrik.codingchallenge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arithmetrik.codingchallenge.networkService.model.DataModel

@Dao
interface DataDao {
    @Query("SELECT * FROM TABLE_DATA")
    fun getAllData(): List<DataModel>

    @Query("SELECT * FROM TABLE_DATA LIMIT :itemLimit OFFSET :offset")
    fun getLimitedData(offset: Int, itemLimit: Int): List<DataModel>

    @Query("SELECT * FROM TABLE_DATA  WHERE title LIKE :searchTerm LIMIT :itemLimit OFFSET :offset")
    fun getSearchedData(offset: Int, itemLimit: Int, searchTerm: String): List<DataModel>

    @Query("SELECT count(*) FROM TABLE_DATA")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(dataList: List<DataModel>)
}