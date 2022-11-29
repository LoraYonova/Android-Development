package com.example.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.CocktailRepository
import com.example.myapplication.viewmodel.CocktailViewModel

class CocktailViewModelFactory(
    private val cocktailRepository: CocktailRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CocktailViewModel(cocktailRepository) as T
    }

}