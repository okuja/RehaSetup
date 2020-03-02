package com.okujajoshua.reha.network.reha.card.response


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


@JsonClass(generateAdapter = true)
data class GetCardAndAccountDetailsResponse(
    val resource: ResourceAccountDetails?,
    val processingTimeInMs: Int,
    val receivedTimestamp: String,
    val errors : List<String>?
)

@JsonClass(generateAdapter = true)
data class ResourceAccountDetails(
    val card: Card
)



