package com.okujajoshua.reha.presentation.signin


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class SigninViewModel : ViewModel(){
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val cardno = MutableLiveData<String>()
    val balance = MutableLiveData<String>()


    init {
        Timber.i("Sign in view model created")
        Timber.i("Initializing the email and password")
        email.value=""
        password.value=""
        cardno.value="5678 9101 1121 3123"
        balance.value="2,000,000"

    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("SigninViewModel destroyed!")
    }
}