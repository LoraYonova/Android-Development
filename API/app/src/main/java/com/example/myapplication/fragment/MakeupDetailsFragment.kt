package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.databinding.MakeupDetailsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MakeupDetailsFragment: Fragment() {

    private lateinit var binding: MakeupDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectMakeupId = arguments?.getInt("id")
        GlobalScope.launch {
            (activity as? MainActivity)?.makeupViewModel?.getMakeupById(selectMakeupId ?: return@launch)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MakeupDetailsBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        GlobalScope.launch {
            (activity as? MainActivity)?.makeupViewModel?.selectedMakeup?.collect{
                binding.makeup = it ?: return@collect

                (activity as? MainActivity)?.runOnUiThread{
                    setDataBinding()

                    Glide
                        .with(context ?: return@runOnUiThread)
                        .load(it.imageLink)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.ivLogo)
                }
            }
        }
    }

    private fun setDataBinding() {
       binding.makeup ?: return

        var makeup = binding.makeup

        if (binding.makeup?.favourite == true) {
            binding.btnLike.setImageResource(android.R.drawable.star_big_on)
        } else {
            binding.btnLike.setImageResource(android.R.drawable.star_big_off)
        }

        binding.tvName.text = "Name: ${makeup?.name}"
        binding.tvBrand.text = "Brand: ${makeup?.brand}"
        binding.tvPrice.text = "Price: ${makeup?.price}"
        binding.tvProductType.text = "Product Type: ${makeup?.productType}"
        binding.tvRating.text = "Rating: ${makeup?.rating}"
        binding.tvDescription.text = "Description: ${makeup?.description}"

        binding.btnLike.setOnClickListener {

            makeup?.favourite = makeup?.favourite != true

            if (makeup?.favourite == true) {
                binding.btnLike.setImageResource(android.R.drawable.star_big_on)
            } else {
                binding.btnLike.setImageResource(android.R.drawable.star_big_off)
            }

            GlobalScope.launch {
                (activity as? MainActivity)?.makeupViewModel?.updateFavourites(
                    makeup ?: return@launch
                )
            }
        }
    }
}