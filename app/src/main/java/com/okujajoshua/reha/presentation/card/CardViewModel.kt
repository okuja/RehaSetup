package com.okujajoshua.reha.presentation.card

import android.app.Application
import androidx.lifecycle.*
import com.amitshekhar.DebugDB
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.repository.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class CardViewModel (application: Application) :
    AndroidViewModel(application) {

    /**
     * The data source this ViewModel will fetch results from.
     */

    private val cardsRepository = CardRepository(RehaDatabase.getInstance(application),application)

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //private val cards = cardsRepository.cards

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


    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)



    init {
        refreshDataFromRepository()
    }


    /**
     * Function for creating card ID
     */

    fun createCardId(){
        viewModelScope.launch {
            try {
                var cardid = cardsRepository.createCardId()
                Timber.d("%s", DebugDB.getAddressLog())
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

    fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                var cards = cardsRepository.refreshCards()
                var x = cards.size
                Timber.d("Size is %d",x)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                val x = networkError
                networkError.printStackTrace()
                // Show a Toast error message and hide the progress bar.
//                if(cards.isEmpty())
//                    _eventNetworkError.value = true
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


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CardViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}