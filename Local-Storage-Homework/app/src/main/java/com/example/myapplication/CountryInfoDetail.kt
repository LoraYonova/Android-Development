package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.CountryInfoBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryInfoDetail(private val countryName: String) : Fragment() {

    lateinit var binding: CountryInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = CountryInfoBinding.inflate(inflater, container, false)

        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://restcountries.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        val countryService = retrofit.create(CountryService::class.java)
        val countryRepository = CountriesRepository(countryService)

        countryRepository.getCountry(countryName)?.enqueue(object : Callback<List<CountryInfo>> {

            override fun onResponse(
                call: Call<List<CountryInfo>>,
                response: Response<List<CountryInfo>>) {

                val details = response.body()?.get(0) ?: return
                Log.d("details", details.toString())
                binding.apply {
                    country = details.name
                    capital = details.capital
                    region = details.region
                    population = details.population.toString()
                    area = details.area.toString()

                    Glide
                        .with(this.root.context)
                        .load(details.flags.png)
                        .fitCenter()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(ivFlag)
                }

                binding.backButton.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()

                }
            }

            override fun onFailure(call: Call<List<CountryInfo>>, t: Throwable) {
                Snackbar.make(binding.root, "Failed to fetch countries", Snackbar.LENGTH_LONG)
                    .show()
            }
        })

        return binding.root
    }

}






