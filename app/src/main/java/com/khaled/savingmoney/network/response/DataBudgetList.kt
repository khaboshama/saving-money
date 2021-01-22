package com.khaled.savingmoney.network.response

import com.google.gson.annotations.SerializedName
import com.khaled.savingmoney.model.Budget

data class DataBudgetList(@SerializedName("budgets") val budgetList: List<Budget>)