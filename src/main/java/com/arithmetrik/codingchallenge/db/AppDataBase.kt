package com.arithmetrik.codingchallenge.db

import Constants.Companion.DATABASE_NAME
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arithmetrik.codingchallenge.networkService.model.DataModel

@Database(entities = [DataModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {
        private var ROOM_INSTANCE: AppDataBase? = null

        @Synchronized
        fun getAppDataBaseInstance(mContext: Context?): AppDataBase? {
            if (ROOM_INSTANCE == null) {
                ROOM_INSTANCE = mContext?.let {
                    Room.databaseBuilder(it, AppDataBase::class.java, DATABASE_NAME).allowMainThreadQueries()
                        .build()
                }
            }
            return ROOM_INSTANCE
        }
    }
}