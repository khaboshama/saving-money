package com.khaled.savingmoney.network.end_points

import com.khaled.savingmoney.network.response.BudgetListResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoneyApi {
    @GET("budgets")
    suspend fun getBudgetsList(): Response<BudgetListResponse>
}