package com.okujajoshua.reha.network.reha.card

import com.okujajoshua.reha.database.card.Card
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateCardIdResponse (
    val resource: Resource?,
    val processingTimeInMs: Int,
    val receivedTimestamp: String,
    val errors : List<String>?
)

@JsonClass(generateAdapter = true)
data class Resource(
    val cards: List<Card>?
)


