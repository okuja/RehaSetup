package com.okujajoshua.reha.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reha_user_table")
data class RehaUser (
    @PrimaryKey(autoGenerate = true)
    var id:Long,

    @ColumnInfo(name = "user_id")
    var userId:Long,

    @ColumnInfo(name = "user_email")
    var email:String,

    @ColumnInfo(name = "user_name")
    var name: String = "",

    @ColumnInfo(name = "card_number")
    var cardNumber: String = "",

    @ColumnInfo(name = "account_balance")
    var accountBalance: String = ""
)