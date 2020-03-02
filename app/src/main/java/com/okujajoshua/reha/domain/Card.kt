package com.okujajoshua.reha.domain

class DomainCard(
    val cardId : String,
    val pan: String
)

class DomainAccount (
    val cardId: String?,
    val accountAliasId : String?,
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

class DomainBalance(
    val accountAliasId: String?,
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






