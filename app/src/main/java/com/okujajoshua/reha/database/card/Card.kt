package com.okujajoshua.reha.database.card

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.okujajoshua.reha.domain.DomainCard
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "cards_table")
data class Card(
    @PrimaryKey
    val cardId : String,
    val pan: String,
    @TypeConverters(AccountsConverter::class)
    val accounts: List<Account>? = null
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
    @TypeConverters(BalancesConverter::class)
    val balances: Balance?,
    val isFunding : Boolean?,
    val isFundingComputed : Boolean?,
    val accountOpenedDate : String?
)

@JsonClass(generateAdapter = true)
data class Balance(
    @Embedded(prefix= "available_")
    val available : Available?,
    @Embedded(prefix="ledger_")
    val ledger : Ledger?,
    @Embedded(prefix="amountowing_")
    val amountOwing : AmountOwing? ,
    @Embedded(prefix="amountdue_")
    val amountDue : AmountDue? ,
    @Embedded(prefix="creditline_")
    val creditLine : CreditLine? ,
    @Embedded(prefix = "availablecredit_")
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

class AccountsConverter {

    @TypeConverter
    fun stringToList(value: String): List<Account> {
        val listAccounts =
            object : TypeToken<List<Account?>?>() {}.type
        return Gson().fromJson(value, listAccounts)
    }

    @TypeConverter
    fun listToString(list: List<Account>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

class BalancesConverter{
    @TypeConverter
    fun stringToBalance(value: String): Balance {
        val balance = object:TypeToken<Balance?>() {}.type
        return Gson().fromJson(value,balance)
    }

    @TypeConverter
    fun balanceToString(balance : Balance) : String{
        val gson = Gson()
        return gson.toJson(balance)
    }
}


fun List<Card>.asDomainModel() : List<DomainCard>{

    return map{
        DomainCard(
            cardId = it.cardId,
            pan = it.pan,
            accounts = it.accounts
        )
    }

}