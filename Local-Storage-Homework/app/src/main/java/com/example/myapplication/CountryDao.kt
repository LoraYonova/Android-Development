package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg : CountryEntity)

}