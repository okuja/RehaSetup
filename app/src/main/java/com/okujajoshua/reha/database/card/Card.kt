package com.okujajoshua.reha.database.card

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cards_table")
data class DatabaseCard constructor(
    @PrimaryKey
    val cardid : String,
    val pan : String,
    val accounts: List<DatabaseAccount>
)


data class DatabaseAccount(
    @PrimaryKey
    val accountAliasId : String?,
    val accountNumber : String?,
    val accountTypeCode : String?,
    val accountTypeDescription:String?,
    val institutionId : String?,
    val accountIndicatorCode: String?,
    val accountIndicatorDescription : String?,
    val balances: DatabaseAccountsBalance?,
    val isFunding : Boolean?,
    val isFundingComputed : Boolean?,
    val accountOpenedDate : String?
)

data class DatabaseAccountsBalance(
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


data class Available (
    val amount : Float?,
    val currencyCode : String?
)

data class Ledger (
    val amount : Float?,
    val currencyCode : String?
)


data class AmountOwing (
    val amount : Float?,
    val currencyCode : String?
)


data class AmountDue (
    val amount : Float?,
    val currencyCode : String?
)


data class CreditLine (
    val amount : Float?,
    val currencyCode : String?
)


data class AvailableCredit (
    val amount : Float?,
    val currencyCode : String?
)