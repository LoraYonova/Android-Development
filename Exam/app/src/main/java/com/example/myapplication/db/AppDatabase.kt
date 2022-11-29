package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.dao.CocktailDao
import com.example.myapplication.db.entity.CocktailEntity

@Database(entities = [CocktailEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}