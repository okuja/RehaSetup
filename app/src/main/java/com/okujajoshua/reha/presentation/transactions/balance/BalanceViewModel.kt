package com.okujajoshua.reha.presentation.transactions.balance

import androidx.lifecycle.ViewModel
import timber.log.Timber

class BalanceViewModel(email:String,password:String) : ViewModel() {

    var email = email
    var password = password

    init {
        Timber.i("Email is $email password is $password")
    }

}