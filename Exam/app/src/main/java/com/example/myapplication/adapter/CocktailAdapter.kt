package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.CocktailsListBinding
import com.example.myapplication.db.entity.CocktailEntity

class CocktailAdapter(private val cocktail: List<CocktailEntity>) :
    RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>(){

        class CocktailViewHolder(val binding: CocktailsListBinding) :
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CocktailsListBinding.inflate(layoutInflater, parent, false)

        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val currentCocktail = cocktail[position]
        holder.binding.apply {
            name = currentCocktail.srtDrink
            ivLiked.visibility = if (currentCocktail.favourite) View.VISIBLE else View.GONE

            Glide
                .with(root.context)
                .load(currentCocktail.strDrinkThumb)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivImage)

            root.setOnClickListener {

            }
        }
    }

    override fun getItemCount(): Int {
        return cocktail.size
    }
}