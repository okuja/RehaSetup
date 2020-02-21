package com.okujajoshua.reha.database.card

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Query("select * from cards_table")
    fun getAllCards(): List<Card>

    @Query("select * from cards_table where cardId = :cardid")
    fun getCardById(cardid :  String): LiveData<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( cards: List<Card>?)
}


