package com.okujajoshua.reha.presentation.signin


import androidx.lifecycle.ViewModel
import timber.log.Timber

class SigninViewModel : ViewModel(){
    init {
        Timber.i("Sign in view model created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("SigninViewModel destroyed!")
    }
}