package com.okujajoshua.reha.presentation.transactions.viewtransactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.TransactionDao
import java.lang.IllegalArgumentException

class ViewTransactionsViewModelFactoy (
    private val email: String,
    private val dataSource: TransactionDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewTransactionsViewModel::class.java)){
            return ViewTransactionsViewModel(email,dataSource) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}