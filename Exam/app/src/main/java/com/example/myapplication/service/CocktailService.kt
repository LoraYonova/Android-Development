package com.example.myapplication.service

import com.example.myapplication.model.CocktailDetailResponse
import com.example.myapplication.model.CocktailListResponse


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailService {


    @GET("filter.php?c=Cocktail")
    fun getCocktails(): Call<CocktailListResponse>

    @GET("lookup.php")
    fun getCocktailById(@Query("i") idDrink: String): Call<CocktailListResponse>
}