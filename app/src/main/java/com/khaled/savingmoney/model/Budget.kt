package com.khaled.savingmoney.model

import com.google.gson.annotations.SerializedName

data class Budget(
    val id: Int,
    val name: String,
    @SerializedName("last_modified_on") val modifiedDate: String,
    @SerializedName("first_month") val firstMonth: String,
    @SerializedName("last_month") val lastMonth: String,
    @SerializedName("date_format") val dateFormat: DateFormat,
    @SerializedName("currency_format") val CurrencyFormat: CurrencyFormat,
)
