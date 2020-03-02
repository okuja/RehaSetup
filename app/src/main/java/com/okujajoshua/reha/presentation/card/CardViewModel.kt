package com.okujajoshua.reha.presentation.card

import android.app.Application
import androidx.lifecycle.*
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.domain.User
import com.okujajoshua.reha.repository.CardRepository
import com.okujajoshua.reha.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class CardViewModel (application: Application,email: String) :
    AndroidViewModel(application) {

    /**
     * The data source this ViewModel will fetch results from.
     */

    private val cardsRepository = CardRepository(RehaDatabase.getInstance(application),application)

    private val userRepository = UserRepository(RehaDatabase.getInstance(application),application)

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val cards = cardsRepository.cards

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
    get() = _user





    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown



    init {

            refreshDataFromRepository(email)
    }



    /**
     * Function for creating card ID
     */

    fun createCardId(){
        viewModelScope.launch {
            try {
                cardsRepository.createCardId("4883836336860016")
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            }catch (networkError:Exception){
                val x = networkError
                networkError.printStackTrace()
                _eventNetworkError.value = true
                Timber.d("Error in creating card id")
            }
        }
    }

    fun refreshDataFromRepository(email: String){
        viewModelScope.launch {
            try {
                _user.value = userRepository.getUserByEmail(email)
                if(user.value!!.verificationStatus) {
                    cardsRepository.refreshCards()
                }
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                val x = networkError
                networkError.printStackTrace()
                // Show a Toast error message and hide the progress bar.
                if(cards.value!!.isEmpty())
                    _eventNetworkError.value = true
            }
        }
    }


    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    class Factory(val app: Application,val email: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CardViewModel(app,email) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}