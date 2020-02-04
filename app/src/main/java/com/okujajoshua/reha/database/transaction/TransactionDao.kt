package com.okujajoshua.reha.database.transaction

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.okujajoshua.reha.database.transaction.Transaction


@Dao
interface TransactionDao {

    @Insert
    fun insert(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

    @Query("SELECT * FROM transactions_table WHERE transaction_id = :key")
    fun getTransactionById(key: Int): Transaction?

    @Query("SELECT * FROM transactions_table WHERE user_email = :email")
    fun getTransactionByEmail(email: String): LiveData<List<Transaction>>


    @Query("DELETE FROM transactions_table")
    fun clear()

}
