package com.okujajoshua.reha.presentation.signup

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.RehaDatabaseDao
import java.lang.IllegalArgumentException

class SignUpViewModelFactory(
    private val dataSource : RehaDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(dataSource,application) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}