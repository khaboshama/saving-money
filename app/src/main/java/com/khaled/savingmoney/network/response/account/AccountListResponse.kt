package com.khaled.savingmoney.network.response.account

import com.google.gson.annotations.SerializedName

data class AccountListResponse(@SerializedName("data") val dataBudgetList: DataAccountList)