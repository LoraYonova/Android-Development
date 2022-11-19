package com.example.myapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.databinding.CryptocurrenciesListItemBinding
import com.example.myapplication.db.entity.CryptoDetails
import com.example.myapplication.fragment.CryptoDetailsFragment

class CryptoAdapter(private val crypto: List<CryptoDetails>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

       class CryptoViewHolder(val binding: CryptocurrenciesListItemBinding) :
               RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CryptocurrenciesListItemBinding.inflate(layoutInflater, parent, false)

        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val currentCrypto = crypto[position]
        holder.binding.apply {
            name = currentCrypto.name
            symbol = currentCrypto.symbol
            currentPrice = currentCrypto.current_price.toString()
            ivLiked.visibility = if (currentCrypto.favourite) View.VISIBLE else View.GONE


            Glide
                .with(root.context)
                .load(currentCrypto.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivImage)

            holder.binding.root.setOnClickListener {
                (it.context as MainActivity).supportFragmentManager.commit {
                    val bundle = Bundle()
                    bundle.putString("name", currentCrypto.name)
                    replace(R.id.container_view, CryptoDetailsFragment::class.java, bundle)
                    addToBackStack("crypto_list_to_details")
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return crypto.size
    }
}