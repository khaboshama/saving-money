package com.khaled.savingmoney.network.response.add_account

import com.google.gson.annotations.SerializedName

data class AddAccountResponse(@SerializedName("data") val dataBudgetList: DataAddAccount)