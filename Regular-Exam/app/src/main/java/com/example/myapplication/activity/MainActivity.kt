package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.adapter.CryptoAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.factory.CryptoViewModelFactory
import com.example.myapplication.repository.CryptoRepository
import com.example.myapplication.service.CryptoService
import com.example.myapplication.util.NetworkUtil
import com.example.myapplication.viewmodel.CryptoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoService: CryptoService
    private lateinit var cryptoRepository: CryptoRepository
    lateinit var cryptoViewModel: CryptoViewModel
    lateinit var db: RoomDatabase

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
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
            cryptoViewModel.getCrypto()
        }
    }

    private fun observeData() {
        GlobalScope.launch{
            cryptoViewModel.cryptoList.collect{
                runOnUiThread{
                    val crypto = it
                    val sortedCrypto = crypto.sortedByDescending { it.current_price }
                    val adapter = CryptoAdapter(sortedCrypto)
                    binding.cryptoList.adapter = adapter
                    binding.tvCryptoCount.text = " Cryptocurrencies: ${it.size}"

                }
            }
        }
    }

    private fun init() {
            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
            "crypto_lora"
            ).build()


            val cryptoDao = (db as AppDatabase).cryptoDao()
            cryptoService = retrofit.create(CryptoService::class.java)
            cryptoRepository = CryptoRepository(this, cryptoService, cryptoDao)
            val cryptoViewModelFactory = CryptoViewModelFactory(cryptoRepository)
            cryptoViewModel =
                ViewModelProvider(this, cryptoViewModelFactory)[CryptoViewModel::class.java]
        }
    }
