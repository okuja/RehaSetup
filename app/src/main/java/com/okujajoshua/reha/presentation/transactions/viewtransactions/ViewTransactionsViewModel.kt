package com.okujajoshua.reha.presentation.transactions.viewtransactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.database.transaction.TransactionDao
import kotlinx.coroutines.*

class ViewTransactionsViewModel(
    email:String ,
    private val dataSource: TransactionDao
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val transactions = dataSource.getTransactionByEmail(email)

    private val _navigateToTransactionDetail = MutableLiveData<Int>()
    val navigateToTransactionDetail: LiveData<Int>
        get() = _navigateToTransactionDetail


    fun onTransactionClicked(id:Int){
        _navigateToTransactionDetail.value = id
    }

    fun onDoneNavigatingToTransactionDetail(){
        _navigateToTransactionDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}