package com.okujajoshua.reha.presentation.profile.viewprofile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okujajoshua.reha.database.RehaUserDao
import com.okujajoshua.reha.database.RehaUser
import kotlinx.coroutines.*

class ViewProfileViewModel(
    val user: RehaUserDao,
    application: Application,
    user_email:String
):AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _currentuser = MutableLiveData<RehaUser?>()
    val currentuser : LiveData<RehaUser?>
    get() = _currentuser


    init{

        initializeCurrentUser(user_email)

    }

    private fun initializeCurrentUser(email:String){
        uiScope.launch {
            _currentuser.value = getCurrentUserFromDatabase(email)
        }
    }

    private suspend fun getCurrentUserFromDatabase(email:String): RehaUser?{

        return withContext(Dispatchers.IO){
            var user = user.getuserbyemail(email)
            user
        }
    }



    //used to cancel all jobs once the viewmodel is destroyed
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}