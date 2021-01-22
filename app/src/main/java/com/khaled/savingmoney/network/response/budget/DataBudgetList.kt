package com.khaled.savingmoney.network.response.budget

import com.google.gson.annotations.SerializedName
import com.khaled.savingmoney.model.budget.Budget

data class DataBudgetList(@SerializedName("budgets") val budgetList: List<Budget>)