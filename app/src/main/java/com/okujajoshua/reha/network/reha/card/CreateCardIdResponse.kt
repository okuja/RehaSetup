package com.okujajoshua.reha.network.reha.card

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateCardIdResponse (
    val resource: List<Card>,
    val processingTimeInMs: Int,
    val receivedTimestamp: String,
    val errors : List<String>
)

@JsonClass(generateAdapter = true)
data class Card(
    val cardId : String,
    val pan: String,
    val accounts: List<Account>
)

@JsonClass(generateAdapter = true)
data class Account(
    val accountAliasId : String,
    val accountNumber : String,
    val accountTypeCode : String,
    val accountTypeDescription:String?,
    val institutionId : String?,
    val accountIndicatorCode: String?,
    val accountIndicatorDescription : String?,
    val balances: Balance,
    val isFunding : Boolean,
    val isFundingComputed : Boolean,
    val accountOpenedDate : Boolean
)

@JsonClass(generateAdapter = true)
data class Balance(
    val available : Available,
    val ledger : Ledger,
    val amountOwing : String? ,
    val amountDue : String? ,
    val creditLine : String? ,
    val availableCredit : String?
)

@JsonClass(generateAdapter = true)
data class Available (
    val amount : Float,
    val currencyCode : String
)

@JsonClass(generateAdapter = true)
data class Ledger (
    val amount : Float,
    val currencyCode : String
)
