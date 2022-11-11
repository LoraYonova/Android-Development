package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.container.setOnClickListener {
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("First View")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHostFragment.navController.navigate(action)
         }

        binding.btnNextActivity.setOnClickListener {
//            val argsBundle = Bundle()
//            argsBundle.putString("title", "Hello, Lora Yonova!")
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("title", "Hello, Lora Yonova!")
            startActivity(intent)
        }

    }
}