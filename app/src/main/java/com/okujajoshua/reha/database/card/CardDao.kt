package com.okujajoshua.reha.database.card

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Query("select * from cards_table")
    fun getAllCards(): LiveData<List<DatabaseCard>>

    @Query("select * from cards_table where cardId = :cardid")
    fun getCardById(cardid :  String): DatabaseCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( cards: List<DatabaseCard>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(card:DatabaseCard?)
}

@Dao
interface AccountDao{

    @Query("SELECT * FROM accounts_table where cardId = :cardid")
    fun getAllByCardId(cardid :  String) : List<DatabaseAccount>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(accounts : List<DatabaseAccount>?)
}

@Dao
interface BalanceDao{

    @Query("SELECT * FROM balance_table where accountAliasId = :accountAliasId")
    fun getAllByAccountAliasId(accountAliasId :  String?) : List<DatabaseBalance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(balances : List<DatabaseBalance>?)
}


