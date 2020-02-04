package com.okujajoshua.reha.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.user.RehaUserDao
import java.lang.IllegalArgumentException

class SigninViewModelFactory(
    private val dataSource: RehaUserDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SigninViewModel::class.java)){
            return SigninViewModel(dataSource)as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}