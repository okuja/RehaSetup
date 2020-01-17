package com.okujajoshua.reha.domain


data class User (
    var id:Long,
    var userId:Long,
    var name: String = "",
    var cardno: String = "",
    var balance: String = ""
)