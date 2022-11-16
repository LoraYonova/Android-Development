package com.example.myapplication

data class Country(
    var name: String,
    var capital: String,
    var flags: Flag
)

data class Flag(
    var svg: String,
    var png: String
)

data class CountryInfo(
    var name: String,
    var capital: String,
    var flags: Flag,
    var region: String,
    var population: Int,
    var area: Int
)