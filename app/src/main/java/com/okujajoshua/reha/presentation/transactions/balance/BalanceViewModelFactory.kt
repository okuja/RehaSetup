package com.okujajoshua.reha.presentation.transactions.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class BalanceViewModelFactory (private val email:String,private val password:String) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BalanceViewModel::class.java)){
            return BalanceViewModel(email,password ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}