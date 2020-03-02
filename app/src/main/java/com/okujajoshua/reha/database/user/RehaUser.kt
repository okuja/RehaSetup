package com.okujajoshua.reha.database.user


import androidx.lifecycle.Transformations.map
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.okujajoshua.reha.domain.User

@Entity(tableName = "reha_user_table")
data class RehaUser(

    @PrimaryKey
    @ColumnInfo(name = "user_email")
    val email: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "second_name")
    val secondName: String,

    @ColumnInfo(name = "telephone")
    val telephone: String,

    @ColumnInfo(name = "password")
    val password: String
)

fun RehaUser.asDomainModel(): User {
    return User(
        firstName, secondName, email, phoneNumber = telephone, verificationStatus = true
    )

}