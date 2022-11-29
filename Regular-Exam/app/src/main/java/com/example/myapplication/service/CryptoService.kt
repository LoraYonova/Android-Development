package com.example.myapplication.service

import com.example.myapplication.model.CryptoResponseDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoService {


    @GET("coins/markets")
    fun getCrypto(@Query("vs_currency") currency: String = "usd"): Call<List<CryptoResponseDetails>>

}