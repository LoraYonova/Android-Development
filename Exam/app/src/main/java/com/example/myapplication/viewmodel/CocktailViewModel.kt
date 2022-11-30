package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.db.entity.CocktailDetailEntity
import com.example.myapplication.db.entity.CocktailEntity
import com.example.myapplication.repository.CocktailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CocktailViewModel(private val cocktailRepository: CocktailRepository) : ViewModel() {

    private val cocktailListStateFlow = MutableStateFlow<List<CocktailEntity>>(arrayListOf())
    private val selectedCocktailStateFlow = MutableStateFlow<CocktailDetailEntity?>(null)
    val cocktailsList: StateFlow<List<CocktailEntity>> = cocktailListStateFlow.asStateFlow()
    val selectedCocktail: StateFlow<CocktailDetailEntity?> = selectedCocktailStateFlow.asStateFlow()

    suspend fun getCocktails() {
        val cocktail = cocktailRepository.getCocktails()
        cocktailListStateFlow.value = cocktail
    }

    suspend fun getCocktailById(id: String) {
        val cocktail = cocktailRepository.getCocktailById(id)
        selectedCocktailStateFlow.value = cocktail ?: return
    }

//    suspend fun updateFavourites(cocktail: CocktailDetailEntity) {
//        cocktailRepository.updateCocktail(cocktail)
//        selectedCocktailStateFlow.value = selectedCocktailStateFlow.value?.copy(favourite = cocktail.favourite)
//    }

}