package com.okujajoshua.reha.network.reha.card

import com.okujajoshua.reha.database.card.DatabaseCard
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
data class Card(
    val cardId : String,
    val pan: String,
    val accounts: List<Account>?
)

@JsonClass(generateAdapter = true)
data class Account(
    val accountAliasId : String?,
    val accountNumber : String?,
    val accountTypeCode : String?,
    val accountTypeDescription:String?,
    val institutionId : String?,
    val accountIndicatorCode: String?,
    val accountIndicatorDescription : String?,
    val balances: Balance?,
    val isFunding : Boolean?,
    val isFundingComputed : Boolean?,
    val accountOpenedDate : String?
)

@JsonClass(generateAdapter = true)
data class Balance(
    val available : Available?,
    val ledger : Ledger?,
    val amountOwing : AmountOwing? ,
    val amountDue : AmountDue? ,
    val creditLine : CreditLine? ,
    val availableCredit : AvailableCredit?
)

@JsonClass(generateAdapter = true)
data class Available (
    val amount : Float?,
    val currencyCode : String?
)

@JsonClass(generateAdapter = true)
data class Ledger (
    val amount : Float?,
    val currencyCode : String?
)

@JsonClass(generateAdapter = true)
data class AmountOwing (
    val amount : Float?,
    val currencyCode : String?
)

@JsonClass(generateAdapter = true)
data class AmountDue (
    val amount : Float?,
    val currencyCode : String?
)

@JsonClass(generateAdapter = true)
data class CreditLine (
    val amount : Float?,
    val currencyCode : String?
)

@JsonClass(generateAdapter = true)
data class AvailableCredit (
    val amount : Float?,
    val currencyCode : String?
)

/**
 * Convert Network results to database objects
 */
fun CreateCardIdResponse.asDatabaseModel(section:String) : List<DatabaseCard>?{
    when(section){
        "card" -> resource?.cards?.map{
            DatabaseCard(
                cardid = it.cardId,
                pan = it.pan,
                accounts = it.accounts
            )
        }

    }
}
