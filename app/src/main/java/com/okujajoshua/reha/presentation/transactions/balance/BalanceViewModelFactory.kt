package com.okujajoshua.reha.presentation.transactions.balance

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class BalanceViewModelFactory(
    private val cardId: String,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BalanceViewModel::class.java)) {
            return BalanceViewModel(cardId,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}