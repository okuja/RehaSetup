package com.okujajoshua.reha.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transactions_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    var id: Int? = null,

    @ColumnInfo(name = "user_email")
    var email: String="",

    @ColumnInfo(name = "card_number")
    var cardNumber: String="",

    @ColumnInfo(name = "amount")
    var amount: String=""

)
