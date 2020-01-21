package com.okujajoshua.reha.presentation.transactions.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class BalanceViewModel(email:String,password:String) : ViewModel() {


    private val _email = MutableLiveData<String>()
    val email : LiveData<String>
        get() = _email
    private val _password = MutableLiveData<String>()
    val password : LiveData<String>
        get() = _password

    init {
        Timber.i("%s",email)
        _email.value = email
        _password.value = password
    }

}