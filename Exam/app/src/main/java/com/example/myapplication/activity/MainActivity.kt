package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.adapter.CocktailAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.factory.CocktailViewModelFactory
import com.example.myapplication.repository.CocktailRepository
import com.example.myapplication.service.CocktailService
import com.example.myapplication.util.NetworkUtil
import com.example.myapplication.viewmodel.CocktailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cocktailService: CocktailService
    private lateinit var cocktailRepository: CocktailRepository
    lateinit var cocktailViewModel: CocktailViewModel
    lateinit var db: RoomDatabase

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeData()

        if (!NetworkUtil.isConnected(this)) {
            Snackbar.make(
                binding.root,
                "No internet connection, information could be outdated",
                Snackbar.LENGTH_LONG
            ).show()
        }

        GlobalScope.launch {
            cocktailViewModel.getCocktails()
        }

    }

    private fun observeData() {
        GlobalScope.launch {
            cocktailViewModel.cocktailsList.collect{
                runOnUiThread{
                    val cocktail = it
                    val sortedCocktail = cocktail.sortedByDescending { it.srtDrink }
                    val adapter = CocktailAdapter(sortedCocktail)
                    binding.cocktailsList.adapter = adapter
                    binding.tvCocktailsCount.text = "Cocktails count: ${it.size}"
                }
            }
        }
    }

    private fun init() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "cocktails_lora_db",
        ).build()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "details",
        ).build()

        val cocktailDao = (db as AppDatabase).cocktailDao()
        cocktailService = retrofit.create(CocktailService::class.java)
        cocktailRepository = CocktailRepository(this, cocktailService, cocktailDao)
        val cocktailViewModelFactory = CocktailViewModelFactory(cocktailRepository)
        cocktailViewModel = ViewModelProvider(this, cocktailViewModelFactory)[CocktailViewModel::class.java]
    }
}