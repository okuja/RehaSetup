package com.okujajoshua.reha.presentation.profile.viewprofile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.okujajoshua.reha.database.RehaDatabaseDao
import com.okujajoshua.reha.database.RehaUser
import kotlinx.coroutines.*

class ViewProfileViewModel(
    val database: RehaDatabaseDao,
    application: Application,
    user_email:String
):AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var currentuser = MutableLiveData<RehaUser?>()

    init{
        initializeCurrentUser(user_email)
    }

    private fun initializeCurrentUser(email:String){
        uiScope.launch {
            currentuser.value = getCurrentUserFromDatabase(email)
        }
    }

    private suspend fun getCurrentUserFromDatabase(email:String): RehaUser?{

        return withContext(Dispatchers.IO){
            var user = database.getuserbyemail(email)
            user
        }
    }



    //used to cancel all jobs once the viewmodel is destroyed
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}