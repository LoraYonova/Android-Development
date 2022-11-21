package com.example.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "makeup_db")
data class MakeupDetails(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "image_link") var imageLink: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "brand") var brand: String,
    @ColumnInfo(name = "price") var price: String,
    @ColumnInfo(name = "rating") var rating: Double,
    @ColumnInfo(name = "product_type") var productType: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "favourite") var favourite: Boolean
)
