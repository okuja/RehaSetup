package com.okujajoshua.reha.network.reha.card.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardActivationBody(
    val securityQuestionToken: SecurityQuestionToken?,
    val birthDateToken: BirthDateToken?,
    val ssnToken: SSNToken?,
    val phoneNumberToken: PhoneNumberToken?,
    val Cvv2 : String?,
    val ExpirationDate: ExpirationDate?
)

@JsonClass(generateAdapter = true)
data class SecurityQuestionToken(
    val isTokenPresent: Boolean,
    val securityQuestionAnswer: String?
)

@JsonClass(generateAdapter = true)
data class BirthDateToken(
    val isTokenPresent: Boolean,
    val birthDateMmDdYyyy: String?
)

@JsonClass(generateAdapter = true)
data class SSNToken(
    val isTokenPresent: Boolean,
    val isLastFourOnly: Boolean,
    val ssn: String?
)

@JsonClass(generateAdapter = true)
data class PhoneNumberToken(
    val isTokenPresent: Boolean,
    val countryCode: String?,
    val phoneNumber: String?
)

@JsonClass(generateAdapter = true)
data class ExpirationDate(
    val yy: String?,
    val mm: String?
)