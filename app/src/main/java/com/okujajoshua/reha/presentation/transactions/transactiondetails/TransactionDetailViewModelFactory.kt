package com.okujajoshua.reha.presentation.transactions.transactiondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.TransactionDao
import java.lang.IllegalArgumentException

class TransactionDetailViewModelFactory(
    private val transaction_id:Int,
    private val dataSource: TransactionDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TransactionDetailViewModel::class.java)){
            return TransactionDetailViewModel(transaction_id,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}