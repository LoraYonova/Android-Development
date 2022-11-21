package com.example.myapplication.service

import com.example.myapplication.model.MakeupResponse
import retrofit2.Call
import retrofit2.http.GET

interface MakeupService {

    @GET("products.json?brand=maybelline")
    fun getMakeup(): Call<List<MakeupResponse>>
}