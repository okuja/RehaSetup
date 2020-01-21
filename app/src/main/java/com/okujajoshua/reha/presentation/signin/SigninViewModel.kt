package com.okujajoshua.reha.presentation.signin


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.database.RehaUserDao
import com.okujajoshua.reha.database.RehaUser
import kotlinx.coroutines.*

class SigninViewModel(
    private val user: RehaUserDao
) : ViewModel(){
    private val _email = MutableLiveData<String>()
    val email:LiveData<String?>
        get() = _email
    val password = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToBalance = MutableLiveData<Boolean?>()
    val navigateToBalance: LiveData<Boolean?>
        get() = _navigateToBalance

    private var _currentuser = MutableLiveData<RehaUser?>()
    val currentuser : LiveData<RehaUser?>
        get() = _currentuser


    fun onSignIn(email:String){
        uiScope.launch {
            _currentuser.value = getCurrentUserFromDatabase(email)
            if(_currentuser.value != null){
                _navigateToBalance.value = true
            }
        }
    }

    private suspend fun getCurrentUserFromDatabase(email: String):RehaUser?{
        return withContext(Dispatchers.IO){
            var user = user.getuserbyemail(email)
            user
        }
    }

    fun doneNavigating(){
        _navigateToBalance.value = null
    }

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }

}