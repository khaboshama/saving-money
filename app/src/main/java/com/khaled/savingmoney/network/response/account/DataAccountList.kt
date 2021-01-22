package com.khaled.savingmoney.network.response.account

import com.google.gson.annotations.SerializedName
import com.khaled.savingmoney.model.account.Account

data class DataAccountList(@SerializedName("accounts") val accountList: List<Account>)