package com.example.myapplication.model

data class CocktailListResponse(
    val drinks: List<Drink>
) {
    data class Drink(
        val idDrink: String,
        val strDrink: String,
        val strDrinkThumb: String
    )
}