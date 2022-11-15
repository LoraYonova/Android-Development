package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database_name"
        ).build()

        val userDao = db.userDao()

        GlobalScope.async {
            val users = userDao.getAll()
            val userFirstName = users.get(0).firstName ?: "No first name"
            binding.usersCount.text = "${users.count()}\n$userFirstName"

//            val user = User(
//                1,
//                "Lora",
//                "Yonova"
//            )
//            userDao.insert(user)
        }


//        val sharedPrefs = getSharedPreferences(
//            "my_shared_pref",
//            Context.MODE_PRIVATE
//        )

//        with(sharedPrefs.edit()) {
//            putString("string_key", "string_value")
////            apply()
//            commit()
//        }
    }
}