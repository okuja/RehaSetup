package com.okujajoshua.reha.network.reha.card.response

import androidx.room.*
import com.okujajoshua.reha.database.card.DatabaseAccount
import com.okujajoshua.reha.database.card.DatabaseBalance
import com.okujajoshua.reha.database.card.DatabaseCard
import com.okujajoshua.reha.domain.DomainCard
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Card(
    @PrimaryKey
    val cardId : String,
    val pan: String,
    val accounts: List<Account>? = null
)

@JsonClass(generateAdapter = true)
data class Account(
    val accountAliasId : String,
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
    val amountOwing : AmountOwing?,
    val amountDue : AmountDue?,
    val creditLine : CreditLine?,
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


fun List<Card>.asDomainModel() : List<DomainCard>{

    return map{
        DomainCard(
            cardId = it.cardId,
            pan = it.pan
        )
    }
}

fun List<Card>.asDatabaseCard() : List<DatabaseCard>{
    return map{
        DatabaseCard(
            cardId = it.cardId,
            pan = it.pan
        )
    }
}

fun Card.asDatabaseCard() : DatabaseCard{
    return DatabaseCard(
        cardId = cardId,
        pan = pan
    )
}

fun List<Account>.asDatabaseAccount(cardId: String) : List<DatabaseAccount>{
    return map{
        DatabaseAccount(
            cardId = cardId,
            accountAliasId = it.accountAliasId,
            accountNumber = it.accountNumber,
            accountTypeCode = it.accountTypeCode,
            accountTypeDescription = it.accountTypeDescription,
            institutionId = it.institutionId,
            accountIndicatorCode = it.accountIndicatorCode,
            accountIndicatorDescription = it.accountIndicatorDescription,
            isFunding =  it.isFunding,
            isFundingComputed = it.isFundingComputed,
            accountOpenedDate = it.accountOpenedDate
        )
    }
}

fun List<Account>.asDatabaseBalance() :List<DatabaseBalance> {

    return map{
        DatabaseBalance(
            accountAliasId = it.accountAliasId,
            availableAmount = it.balances?.available?.amount,
            availableCurrencyCode = it.balances?.available?.currencyCode,
            ledgerAmount = it.balances?.ledger?.amount,
            ledgerCurrencyCode = it.balances?.ledger?.currencyCode,
            amountOwingAmount = it.balances?.amountOwing?.amount,
            amountOwingCurrencyCode = it.balances?.amountOwing?.currencyCode,
            amountDueAmount = it.balances?.amountDue?.amount,
            amountDueCurrencyCode = it.balances?.amountDue?.currencyCode,
            creditLineAmount = it.balances?.creditLine?.amount,
            creditLineCurrencyCode = it.balances?.creditLine?.currencyCode,
            availableCreditAmount = it.balances?.availableCredit?.amount,
            availableCreditCurrencyCode = it.balances?.availableCredit?.currencyCode
        )
    }
}