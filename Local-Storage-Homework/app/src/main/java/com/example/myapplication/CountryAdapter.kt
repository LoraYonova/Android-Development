package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.CountryListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CountryAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    lateinit var contex: Context

    class CountryViewHolder(val binding: CountryListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryListBinding.inflate(layoutInflater, parent, false)

        return CountryViewHolder(binding)
    }

    val db = Room.databaseBuilder(
        contex.applicationContext,
        CountryDatabase::class.java,
        "country_db"
    ).build()

    val countryDao = db.countryDao()


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {


        val currentCountry = countries[position]
        holder.binding.apply {
            country = currentCountry.name
            capital = currentCountry.capital

            Glide
                .with(this.root.context)
                .load(currentCountry.flags.png)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivFlag)

            GlobalScope.async {
//               TODO
            }


            holder.binding.root.setOnClickListener {
                Snackbar.make(it, currentCountry.name, Snackbar.LENGTH_LONG).show()
                val activity = it.context as AppCompatActivity
                val transaction = activity.supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragment_container_view, CountryInfoDetail(currentCountry.name)
                )
                transaction.addToBackStack("first_transaction")
                transaction.commit()
            }
        }

    }
        override fun getItemCount() = countries.size
}