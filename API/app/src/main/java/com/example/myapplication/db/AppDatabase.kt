package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.dao.MakeupDao
import com.example.myapplication.db.entity.MakeupDetails

@Database(entities = [MakeupDetails::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun makeupDao(): MakeupDao
}