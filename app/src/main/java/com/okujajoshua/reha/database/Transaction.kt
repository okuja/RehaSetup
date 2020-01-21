package com.okujajoshua.reha.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transactions_table")
data class Transaction(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,

    @PrimaryKey
    @ColumnInfo(name = "user_email")
    val email: String,

    @ColumnInfo(name = "card_number")
    val cardNumber: String,

    @ColumnInfo(name = "amount")
    val amount: String

)
