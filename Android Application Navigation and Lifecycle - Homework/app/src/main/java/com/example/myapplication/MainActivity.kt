package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextActivity.setOnClickListener {
//            val argsBundle = Bundle()
//            argsBundle.putString("title", "Hello, Lora Yonova!")
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("title", "Hello, Lora Yonova!")
            startActivity(intent)
        }


        binding.container.setOnClickListener() {

            val action: NavDirections
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

            val currentFragment = navHostFragment.childFragmentManager.fragments.last()

            action = when(currentFragment) {
                is FirstFragment -> FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                is SecondFragment -> SecondFragmentDirections.actonSecondFragmentToThirdFragment()
                is ThirdFragment -> ThirdFragmentDirections.actionThirdFragmentToFourthFragment()
                else -> FourthFragmentDirections.actionFourthFragmentToFirstFragment()
            }

            navHostFragment.navController.navigate(action)
        }



    }
}