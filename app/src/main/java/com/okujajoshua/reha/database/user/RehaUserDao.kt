package com.okujajoshua.reha.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.okujajoshua.reha.database.user.RehaUser

@Dao
interface RehaUserDao {

    @Insert
    fun insertuser(user: RehaUser)

    @Update
    fun updateuser(user: RehaUser)

    @Query("SELECT * FROM reha_user_table WHERE user_email = :email")
    fun getuserbyemail(email: String): RehaUser?


    @Query("DELETE FROM reha_user_table")
    fun clear()
    


}