package com.okujajoshua.reha.presentation.signup

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.user.RehaUserDao
import java.lang.IllegalArgumentException

class SignUpViewModelFactory(
    private val dataSource : RehaUserDao,
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