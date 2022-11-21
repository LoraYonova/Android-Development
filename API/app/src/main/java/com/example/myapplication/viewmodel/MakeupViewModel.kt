package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.db.entity.MakeupDetails
import com.example.myapplication.repository.MakeupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MakeupViewModel(
    private val makeupRepository: MakeupRepository
) : ViewModel() {

    private val makeupListStateFlow = MutableStateFlow<List<MakeupDetails>>(arrayListOf())
    private val selectedMakeupStateFlow = MutableStateFlow<MakeupDetails?>(null)
    val makeupList: StateFlow<List<MakeupDetails>> = makeupListStateFlow.asStateFlow()
    val selectedMakeup: StateFlow<MakeupDetails?> = selectedMakeupStateFlow.asStateFlow()

    suspend fun getMakeup() {
        val makeup = makeupRepository.getMakeup()
        makeupListStateFlow.value = makeup
    }

    suspend fun getMakeupById(id: Int) {
        val makeup = makeupRepository.getMakeupById(id)
        selectedMakeupStateFlow.value = makeup
    }

    suspend fun updateFavourites(makeup: MakeupDetails) {
        makeupRepository.updateMakeup(makeup)
        selectedMakeupStateFlow.value =
            selectedMakeupStateFlow.value?.copy(favourite = makeup.favourite)
    }


}