package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.db.entity.CocktailEntity
import com.example.myapplication.repository.CocktailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CocktailViewModel(private val cocktailRepository: CocktailRepository) : ViewModel() {

    private val cocktailListStateFlow = MutableStateFlow<List<CocktailEntity>>(arrayListOf())
    private val selectedCocktailStateFlow = MutableStateFlow<CocktailEntity?>(null)
    val cocktailsList: StateFlow<List<CocktailEntity>> = cocktailListStateFlow.asStateFlow()
    val selectedCocktail: StateFlow<CocktailEntity?> = selectedCocktailStateFlow.asStateFlow()

    suspend fun getCocktails() {
        val cocktail = cocktailRepository.getCocktails()
        cocktailListStateFlow.value = cocktail
    }

    suspend fun updateFavourites(cocktail: CocktailEntity) {
        cocktailRepository.updateCocktail(cocktail)
        selectedCocktailStateFlow.value = selectedCocktailStateFlow.value?.copy(favourite = cocktail.favourite)
    }

}