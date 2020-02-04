package com.okujajoshua.reha.database.user


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reha_user_table")
data class RehaUser (

    @PrimaryKey
    @ColumnInfo(name = "user_email")
    val email:String,

    @ColumnInfo(name = "first_name")
    val firstName: String ,

    @ColumnInfo(name = "second_name")
    val secondName: String ,

    @ColumnInfo(name = "telephone")
    val telephone: String ,

    @ColumnInfo(name = "password")
    val password: String
)