package com.okujajoshua.reha.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RehaDatabaseDao {

    @Insert
    fun insertuser(user: RehaUser)

    @Update
    fun updateuser(user: RehaUser)

    @Query("SELECT * FROM reha_user_table WHERE user_id = :key")
    fun getuser(key: Long): RehaUser?

    @Query("DELETE FROM reha_user_table")
    fun clear()


}