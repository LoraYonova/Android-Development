package com.example.myapplication

import android.content.Intent
import android.net.Uri
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

//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, FirstFragment())
//        transaction.addToBackStack("first_transaction")
//        transaction.commit()

        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("some value")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHostFragment.navController.navigate(action)


        binding.btnNextActivity.setOnClickListener {
            val argsBundle = Bundle()
            argsBundle.putString("title", "Second Activity Title")
            val intent = Intent(this, SecondActivity::class.java)
            // intent.putExtras(argsBundle)
            intent.putExtra("title", "Second Activity Title")
            startActivity(intent)
        }

        binding.btnBrowserIntent.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(browserIntent)
        }

        binding.btnMailIntent.setOnClickListener {
            val subject = "My email subject"
            val recipient = "myemail@gmail.com"
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$recipient?subject=$subject"))
            startActivity(Intent.createChooser(emailIntent, "Send email via:"))
        }
    }
}