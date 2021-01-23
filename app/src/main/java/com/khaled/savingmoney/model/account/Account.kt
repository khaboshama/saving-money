package com.khaled.savingmoney.model.account

data class Account(
    val id: String,
    val name: String,
    val type: String,
    val close: Boolean,
    val balance: Int,
    val deleted: Boolean
)