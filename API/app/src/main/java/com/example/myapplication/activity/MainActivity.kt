package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.adapter.MakeupAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.factory.MakeupViewModelFactory
import com.example.myapplication.repository.MakeupRepository
import com.example.myapplication.service.MakeupService
import com.example.myapplication.util.NetworkUtil
import com.example.myapplication.viewmodel.MakeupViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var makeupService: MakeupService
    private lateinit var makeupRepository: MakeupRepository
    lateinit var makeupViewModel: MakeupViewModel
    lateinit var db: RoomDatabase

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://makeup-api.herokuapp.com/api/v1/")
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
            makeupViewModel.getMakeup()
        }
    }

    private fun observeData() {
        GlobalScope.launch {
            makeupViewModel.makeupList.collect{
                runOnUiThread{
                    val makeup = it
                    val sortedMakeup = makeup.sortedByDescending { it.price }
                    val adapter = MakeupAdapter(sortedMakeup)
                    binding.makeupList.adapter = adapter
                    binding.tvMakeupCount.text = "Makeup Cont: ${it.size}"
                }
            }
        }
    }

    private fun init() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "makeup_db"
        ).build()

        val makeupDao = (db as AppDatabase).makeupDao()
        makeupService = retrofit.create(MakeupService::class.java)
        makeupRepository = MakeupRepository(this, makeupService, makeupDao)
        val makeupViewModelFactory = MakeupViewModelFactory(makeupRepository)
        makeupViewModel = ViewModelProvider(this, makeupViewModelFactory)[MakeupViewModel::class.java]


    }
}