package com.example.myapplication.service

import com.example.myapplication.model.CryptoResponseDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {

    @GET("coins/markets?vs_currency=usd")
    fun getCrypto(): Call<List<CryptoResponseDetails>>

    @GET("coins/markets?vs_currency=usd/{name}")
    fun getCryptoByName(@Path("name") name: String): Call<List<CryptoResponseDetails>>
}