package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.dao.CryptoDao
import com.example.myapplication.db.entity.CryptoDetails

@Database(entities = [CryptoDetails::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao
}