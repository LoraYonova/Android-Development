package com.example.myapplication.model

data class CryptoResponseDetails(
    var image: String,
    var name: String,
    var symbol: String,
    var current_price: Float,
    var market_cap: Float?,
    var high_24h: Float?,
    var price_change_percentage_24h: Float?,
    var market_cap_change_percentage_24h: Float?,
    var low_24h: Float?
)
