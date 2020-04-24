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

    @Query("SELECT count(*) FROM TABLE_DATA")
    fun getCount(): Int

    @Query("SELECT * FROM TABLE_DATA WHERE title LIKE :searchTerm")
    fun search(searchTerm: String): List<DataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(dataList: List<DataModel>)
}