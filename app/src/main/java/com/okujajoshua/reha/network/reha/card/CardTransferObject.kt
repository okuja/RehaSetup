package com.okujajoshua.reha.network.reha.card

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardActivationBody(
    val cardIdModel : List<CardIdModel>
)

@JsonClass(generateAdapter = true)
data class CardIdModel(
    val pan : String,
    val lookUpBalances: Boolean = true
)