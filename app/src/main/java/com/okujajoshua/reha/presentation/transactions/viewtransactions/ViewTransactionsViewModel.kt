package com.okujajoshua.reha.presentation.transactions.viewtransactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.database.Transaction
import com.okujajoshua.reha.database.TransactionDao
import kotlinx.coroutines.*

class ViewTransactionsViewModel(
    email:String ,
    private val dataSource:TransactionDao): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val transactions = dataSource.getTransactionByEmail(email)

//    private val _transactions = MutableLiveData<LiveData<List<Transaction>>>()
//    val transactions: LiveData<LiveData<List<Transaction>>>
//        get() = _transactions

//    init {
//        initializeTransactions(email)
//    }

//    fun initializeTransactions(email: String){
//        uiScope.launch {
//            _transactions.value = getUsersTransactions(email)
//        }
//    }
//
//    private suspend fun getUsersTransactions(email: String):LiveData<List<Transaction>>?{
//        return withContext(Dispatchers.IO){
//            var transactions = dataSource.getTransactionByEmail(email)
//            transactions
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}