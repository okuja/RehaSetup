package com.okujajoshua.reha.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TransactionDao {

    @Insert
    fun insert(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

//    @Query("SELECT * FROM transactions_table WHERE id = :key")
//    fun get(key: Long): Transaction?

    @Query("SELECT * FROM transactions_table WHERE user_email = :email")
    fun getTransactionByEmail(email: String): LiveData<List<Transaction>>


    @Query("DELETE FROM transactions_table")
    fun clear()

}
