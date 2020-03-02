package com.okujajoshua.reha.domain

//TODO: Handle user verification status
data class User (
    val firstName: String,
    val secondName : String,
    val email : String,
    val phoneNumber:String,
    val verificationStatus:Boolean = false
)