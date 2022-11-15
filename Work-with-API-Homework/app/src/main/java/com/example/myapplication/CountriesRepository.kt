package com.example.myapplication

import retrofit2.Call

class CountriesRepository constructor(
    private val countryService: CountryService
) {
    fun getCountries(): Call<List<Country>>? {
        return try {
            countryService.getCountries()
        } catch (e: Exception) {
             null
        }
    }

    fun getCountry(name: String): Call<List<CountryInfo>>? {
        return try {
            countryService.getCountry(name)
        } catch (e: Exception) {
            null
        }
    }
}