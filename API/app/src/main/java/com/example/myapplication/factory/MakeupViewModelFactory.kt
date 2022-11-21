package com.example.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.MakeupRepository
import com.example.myapplication.viewmodel.MakeupViewModel

class MakeupViewModelFactory(
    private val makeupRepository: MakeupRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MakeupViewModel(makeupRepository) as T
    }
}