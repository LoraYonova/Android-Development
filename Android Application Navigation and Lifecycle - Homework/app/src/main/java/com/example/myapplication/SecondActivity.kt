package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import com.example.myapplication.databinding.SecondActivityBinding

class SecondActivity: Activity() {

    lateinit var binding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val title = intent.extras?.getString("title") ?: "Default Title"
        binding.tvTitle.text = title

        binding.btnPreviousActivity.setOnClickListener {
            finish()
        }
    }
}