package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "country_name") val countryName: String,
    @ColumnInfo(name = "capital") val capital: String
//    @ColumnInfo(name = "region") val region: String,
//    @ColumnInfo(name = "population") val population: Int,
//    @ColumnInfo(name = "area") val area: Int
)