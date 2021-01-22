package com.khaled.savingmoney.model

import com.google.gson.annotations.SerializedName

data class CurrencyFormat(
    @SerializedName("iso_code")val isoCode: String,
    @SerializedName("example_format")val format: String,
    @SerializedName("decimal_digits")val decimalDigits: String,
    @SerializedName("decimal_separator")val decimalSeparator: String,
    @SerializedName("symbol_first")val symbolFirst: Boolean,
    @SerializedName("group_separator")val groupSeparator: String,
    @SerializedName("currency_symbol")val currencySymbol: String,
    @SerializedName("display_symbol")val displaySymbol: Boolean,
)