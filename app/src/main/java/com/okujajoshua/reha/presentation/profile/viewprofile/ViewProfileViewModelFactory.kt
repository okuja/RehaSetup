package com.okujajoshua.reha.presentation.profile.viewprofile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.database.user.RehaUserDao
import java.lang.IllegalArgumentException

class ViewProfileViewModelFactory(
    private val dataSource: RehaUserDao,
    private val application : Application,
    private val user_email: String
):ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewProfileViewModel::class.java)){
            return ViewProfileViewModel(dataSource,application,user_email) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}