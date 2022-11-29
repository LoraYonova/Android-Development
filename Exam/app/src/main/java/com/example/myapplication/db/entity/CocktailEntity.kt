package com.example.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails_lora_db")
data class CocktailEntity(
    @PrimaryKey var idDrink: String,
    @ColumnInfo(name = "srt_drink") var srtDrink: String,
    @ColumnInfo(name = "str_drink_thumb") var strDrinkThumb: String,
    @ColumnInfo(name = "favourite") var favourite: Boolean
)
