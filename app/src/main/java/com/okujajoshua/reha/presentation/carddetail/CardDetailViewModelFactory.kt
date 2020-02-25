package com.okujajoshua.reha.presentation.carddetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CardDetailViewModelFactory(
    private val cardId: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardDetailViewModel::class.java)) {
            return CardDetailViewModel(cardId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}