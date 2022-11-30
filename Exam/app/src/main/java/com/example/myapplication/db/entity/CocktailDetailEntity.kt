package com.example.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class CocktailDetailEntity(
    @PrimaryKey var idDrink: String,
    @ColumnInfo(name = "srt_drink") var srtDrink: String,
    @ColumnInfo(name = "str_drink_thumb") var strDrinkThumb: String,
    @ColumnInfo(name = "srt_glass")  val strGlass: String,
    @ColumnInfo(name = "str_ingredient1") val strIngredient1: String,
    @ColumnInfo(name = "str_measure1") val strMeasure1: String,
    @ColumnInfo(name = "str_instructions") val strInstructions: String
)
