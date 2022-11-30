package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.databinding.CocktailDetailsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CocktailDetailFragment : Fragment() {

    private lateinit var binding: CocktailDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedCocktailId = arguments?.getString("idDrink", null)
        GlobalScope.launch {
            (activity as? MainActivity)?.cocktailViewModel?.getCocktailById(selectedCocktailId ?: return@launch)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CocktailDetailsBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        GlobalScope.launch {
            (activity as? MainActivity)?.cocktailViewModel?.selectedCocktail?.collect{
                binding.cocktail = it ?: return@collect

                (activity as? MainActivity)?.runOnUiThread {
                    setDataBinding()
                    Glide
                        .with(context ?: return@runOnUiThread)
                        .load(it.strDrinkThumb)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.ivLogo)
                }
            }
        }
    }

    private fun setDataBinding() {
       binding.cocktail ?: return

        var cocktail = binding.cocktail

        binding.tvName.text = "Name ${cocktail?.srtDrink}"
        binding.tvGlass.text = "Glass ${cocktail?.strGlass}"
        binding.tvMeasure.text = "Measure ${cocktail?.strMeasure1}"
        binding.tvIngredient.text = "Ingredient ${cocktail?.strIngredient1}"
        binding.tvInstruction.text = "Instruction ${cocktail?.strInstructions}"

    }

}
