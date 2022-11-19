package com.example.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CryptoDetails(
    @PrimaryKey
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "symbol") var symbol: String,
    @ColumnInfo(name = "current_price") var current_price: Float,
    @ColumnInfo(name = "market_cap") var market_cap: Float,
    @ColumnInfo(name = "high_24h") var high_24h: Float,
    @ColumnInfo(name = "price_change_percentage_24h") var price_change_percentage_24h: Float,
    @ColumnInfo(name = "market_cap_change_percentage_24h") var market_cap_change_percentage_24h: Float,
    @ColumnInfo(name = "low_24h") var low_24h: Float,
    @ColumnInfo(name = "favourite") var favourite: Boolean

)
