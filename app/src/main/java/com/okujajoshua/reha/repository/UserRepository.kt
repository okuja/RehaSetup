package com.okujajoshua.reha.repository

import android.app.Application
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.database.user.asDomainModel
import com.okujajoshua.reha.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val database: RehaDatabase, private val application: Application) {


    suspend fun getUserByEmail(userEmail:String) : User {
        return withContext(Dispatchers.IO) {
            database.rehaUserDao.getuserbyemail(userEmail).asDomainModel()
        }
    }
}