package com.okujajoshua.reha.presentation.transactions.addmoney

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.database.Transaction
import com.okujajoshua.reha.database.TransactionDao
import kotlinx.coroutines.*

class AddMoneyViewModel(
    val email: String,
    private val dataSource: TransactionDao): ViewModel() {

    val _useremail = MutableLiveData<String>()
    val useremail: LiveData<String>
            get()= _useremail

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _useremail.value = email
    }



    fun onSaveTransaction(email:String ,cardNumber:String,amount:String){
        uiScope.launch{
            val newTransaction = Transaction(email, cardNumber, amount)

            withContext(Dispatchers.IO){
                dataSource.insert(newTransaction)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}