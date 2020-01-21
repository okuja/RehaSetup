package com.okujajoshua.reha.presentation.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.okujajoshua.reha.database.RehaUserDao
import com.okujajoshua.reha.database.RehaUser
import kotlinx.coroutines.*
import timber.log.Timber

class SignUpViewModel(
    val user : RehaUserDao,
    application : Application
):AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var rehauser = MutableLiveData<RehaUser?>()

    fun onCreateUser(email:String,first_name:String,second_name:String,telephone:String,password:String){
        uiScope.launch {
//            val newUser = RehaUser(1,1,"ajokuja@gmail.com","Okuja","1234 1113 4355","2 000 000")
            val newUser = RehaUser(1,"23",email,first_name,second_name,telephone,password)

            Timber.i("User is %s %s %s %s",email,first_name,second_name,telephone)

            insertNewUser(newUser)
        }
    }

    private suspend fun insertNewUser(user: RehaUser){
        withContext(Dispatchers.IO){
            this@SignUpViewModel.user.insertuser(user)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}