package com.okujajoshua.reha.presentation.carddetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.domain.DomainAccount
import com.okujajoshua.reha.domain.DomainBalance
import com.okujajoshua.reha.repository.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class CardDetailViewModel(
    val cardid: String,
    application: Application
) : AndroidViewModel(application) {

    private val cardsRepository = CardRepository(RehaDatabase.getInstance(application),application)

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    var cardId = cardid

    private val _accounts = MutableLiveData<List<DomainAccount>>()
    val accounts : LiveData<List<DomainAccount>>
        get() = _accounts

    private val _balances = MutableLiveData<List<DomainBalance>>()
    val balances : LiveData<List<DomainBalance>>
        get() = _balances


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


    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    init {
        refreshDataFromRepository(cardId)
    }

    fun refreshDataFromRepository(cardId: String){
        viewModelScope.launch {
            try {
                cardsRepository.refreshCardAccountDetails(cardId)

                _accounts.value = cardsRepository.getCardAccounts(cardId)

                val accountAliasId = accounts.value?.get(0)?.accountAliasId
                _balances.value = cardsRepository.getAccountBalance(accountAliasId)

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                val x = networkError
                networkError.printStackTrace()
                // Show a Toast error message and hide the progress bar.
                if(accounts.value!!.isEmpty())
                    _eventNetworkError.value = true
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}