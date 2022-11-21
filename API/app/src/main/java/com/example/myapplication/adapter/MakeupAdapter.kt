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
import com.example.myapplication.databinding.MakeupListBinding
import com.example.myapplication.db.entity.MakeupDetails
import com.example.myapplication.fragment.MakeupDetailsFragment

class MakeupAdapter(private val makeup: List<MakeupDetails>) :
    RecyclerView.Adapter<MakeupAdapter.MakeupViewHolder>() {

    class MakeupViewHolder(val binding: MakeupListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeupViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MakeupListBinding.inflate(layoutInflater, parent, false)
        return MakeupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MakeupViewHolder, position: Int) {
        val currentMakeup = makeup[position]
        holder.binding.apply {
            name = currentMakeup.name
            price = currentMakeup.price
            ivLiked.visibility = if (currentMakeup.favourite) View.VISIBLE else View.GONE


            Glide
                .with(root.context)
                .load(currentMakeup.imageLink)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivImage)

            root.setOnClickListener {
                (it.context as MainActivity).supportFragmentManager.commit {
                    val bundle = Bundle()
                    bundle.putInt("id", currentMakeup.id)
                    replace(R.id.container_view, MakeupDetailsFragment::class.java, bundle)
                    addToBackStack("makeup_list_to_details")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return makeup.size
    }
}