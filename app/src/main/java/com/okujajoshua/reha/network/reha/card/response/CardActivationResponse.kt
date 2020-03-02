package com.okujajoshua.reha.network.reha.card.response


data class CardActivationResponse(
    val errors:List<String>?,
    val receivedTimestamp: String?,
    val processingTimeInMs: String?,
    val resource: Status
)

data class Status(
    val isCardActivationSuccessful:Boolean?
)