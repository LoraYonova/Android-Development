package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.databinding.AcivitySecondBinding

class SecondActivity: Activity() {

    lateinit var binding: AcivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AcivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val title = intent.extras?.getString("title") ?: "Default Title"
        binding.tvTitle.text = title

        binding.btnPreviousActivity.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            finish()
        }

    }
}