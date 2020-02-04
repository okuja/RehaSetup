package com.okujajoshua.reha.presentation.transactions.transactiondetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.database.transaction.Transaction
import com.okujajoshua.reha.database.transaction.TransactionDao
import kotlinx.coroutines.*

class TransactionDetailViewModel(
    transaction_id:Int,
    val dataSource: TransactionDao
) : ViewModel() {

    private val _navigateToTransactions = MutableLiveData<Boolean?>()
    val navigateToTransactions : LiveData<Boolean?>
            get() = _navigateToTransactions

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _transaction = MutableLiveData<Transaction?>()
        val transaction : LiveData<Transaction?>
        get() = _transaction

    init {
        getTransaction(transaction_id)
    }

    fun getTransaction(id:Int){
        uiScope.launch {
            _transaction.value = getTransactionById(id)
        }
    }

    private suspend fun getTransactionById(id:Int): Transaction?{
        return withContext(Dispatchers.IO){
            var transaction = dataSource.getTransactionById(id)
            transaction
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Call this immediately after navigating to [SleepTrackerFragment]
     */
    fun doneNavigating() {
        _navigateToTransactions.value = null
    }

    fun onClose() {
        _navigateToTransactions.value = true
    }
}