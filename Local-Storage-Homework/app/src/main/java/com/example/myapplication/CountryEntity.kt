package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "area") val area: Int
)