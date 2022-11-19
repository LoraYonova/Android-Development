package com.example.myapplication.viewmodel


import androidx.lifecycle.ViewModel
import com.example.myapplication.db.entity.CryptoDetails
import com.example.myapplication.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CryptoViewModel(
    private val cryptoRepository: CryptoRepository
    ): ViewModel() {

        private val cryptoListStateFlow = MutableStateFlow<List<CryptoDetails>>(arrayListOf())
        private val selectedCryptoStateFlow = MutableStateFlow<CryptoDetails?>(null)
        val cryptoList: StateFlow<List<CryptoDetails>> = cryptoListStateFlow.asStateFlow()
        val selectedCrypto: StateFlow<CryptoDetails?> = selectedCryptoStateFlow.asStateFlow()

        suspend fun getCrypto() {
            val crypto = cryptoRepository.getCrypto()
            cryptoListStateFlow.value = crypto
        }

    suspend fun getCryptoByName(name: String) {
        val crypto = cryptoRepository.getCryptoByName(name)
        selectedCryptoStateFlow.value = crypto ?: return
    }

    suspend fun updateFavourites(crypto: CryptoDetails) {
        cryptoRepository.updateCrypto(crypto)
        selectedCryptoStateFlow.value =
            selectedCryptoStateFlow.value?.copy(favourite = crypto.favourite)
    }

}