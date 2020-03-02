package com.okujajoshua.reha.database.card

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.okujajoshua.reha.domain.DomainAccount
import com.okujajoshua.reha.domain.DomainBalance
import com.okujajoshua.reha.domain.DomainCard
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "cards_table")
data class DatabaseCard(
    @PrimaryKey
    val cardId : String,
    val pan: String
)

@JsonClass(generateAdapter = true)
@Entity(tableName = "accounts_table")
data class DatabaseAccount(
    val cardId: String,
    @PrimaryKey
    val accountAliasId : String,
    val accountNumber : String?,
    val accountTypeCode : String?,
    val accountTypeDescription:String?,
    val institutionId : String?,
    val accountIndicatorCode: String?,
    val accountIndicatorDescription : String?,
    val isFunding : Boolean?,
    val isFundingComputed : Boolean?,
    val accountOpenedDate : String?
)

@JsonClass(generateAdapter = true)
@Entity(tableName = "balance_table")
data class DatabaseBalance(
    @PrimaryKey
    val accountAliasId: String,
    val availableAmount :  Float?,
    val availableCurrencyCode:String?,
    val ledgerAmount :  Float?,
    val ledgerCurrencyCode:String?,
    val amountOwingAmount :  Float?,
    val amountOwingCurrencyCode:String?,
    val amountDueAmount :  Float?,
    val amountDueCurrencyCode:String?,
    val creditLineAmount :  Float?,
    val creditLineCurrencyCode:String?,
    val availableCreditAmount :  Float?,
    val availableCreditCurrencyCode:String?
)



fun List<DatabaseCard>.asDomainModel() : List<DomainCard>{

    return map{
        DomainCard(
            cardId = it.cardId,
            pan = it.pan
        )
    }
}
fun DatabaseCard.asDomainModel() : DomainCard{

    return DomainCard(
            cardId = cardId,
            pan = pan
        )
}



fun List<DatabaseAccount>.asDomainAccount(cardId: String) : List<DomainAccount>{
    return map{
        DomainAccount(
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

fun DatabaseBalance.asDomainBalance(accountAliasId: String?) : DomainBalance{

    return DomainBalance(
        accountAliasId = accountAliasId,
        availableAmount = availableAmount,
        availableCurrencyCode = availableCurrencyCode,
        ledgerAmount = ledgerAmount,
        ledgerCurrencyCode = ledgerCurrencyCode,
        amountOwingAmount = amountOwingAmount,
        amountOwingCurrencyCode = amountOwingCurrencyCode,
        amountDueAmount = amountDueAmount,
        amountDueCurrencyCode = amountDueCurrencyCode,
        creditLineAmount = creditLineAmount,
        creditLineCurrencyCode = creditLineCurrencyCode,
        availableCreditAmount = availableCreditAmount,
        availableCreditCurrencyCode = availableCreditCurrencyCode
    )
}

fun List<DatabaseBalance>.asDomainBalance(accountAliasId: String?) : List<DomainBalance>{

    return map{
        DomainBalance(
            accountAliasId = accountAliasId,
            availableAmount = it.availableAmount,
            availableCurrencyCode = it.availableCurrencyCode,
            ledgerAmount = it.ledgerAmount,
            ledgerCurrencyCode = it.ledgerCurrencyCode,
            amountOwingAmount = it.amountOwingAmount,
            amountOwingCurrencyCode = it.amountOwingCurrencyCode,
            amountDueAmount = it.amountDueAmount,
            amountDueCurrencyCode = it.amountDueCurrencyCode,
            creditLineAmount = it.creditLineAmount,
            creditLineCurrencyCode = it.creditLineCurrencyCode,
            availableCreditAmount = it.availableCreditAmount,
            availableCreditCurrencyCode = it.availableCreditCurrencyCode
        )
    }

}



