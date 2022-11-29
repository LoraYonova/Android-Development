package com.example.myapplication.service

import com.example.myapplication.model.CocktailListResponse



import retrofit2.Call
import retrofit2.http.GET

interface CocktailService {


    @GET("filter.php?c=Cocktail")
    fun getCocktails(): Call<List<CocktailListResponse>>
}