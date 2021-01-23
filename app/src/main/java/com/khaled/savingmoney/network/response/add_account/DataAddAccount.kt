package com.khaled.savingmoney.network.response.add_account

import com.google.gson.annotations.SerializedName
import com.khaled.savingmoney.model.account.Account

data class DataAddAccount(@SerializedName("account") val account: Account)