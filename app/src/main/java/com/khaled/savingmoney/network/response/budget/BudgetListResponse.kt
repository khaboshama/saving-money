package com.khaled.savingmoney.network.response.budget

import com.google.gson.annotations.SerializedName

data class BudgetListResponse(@SerializedName("data") val dataBudgetList: DataBudgetList)