package com.okujajoshua.reha.presentation.transactions.addmoney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.transaction.TransactionDao
import java.lang.IllegalArgumentException


class AddMoneyViewModelFactory(
    val email:String,
    private val dataSource : TransactionDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddMoneyViewModel::class.java)){
            return AddMoneyViewModel(email,dataSource) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}