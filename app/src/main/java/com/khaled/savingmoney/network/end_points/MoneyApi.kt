package com.khaled.savingmoney.network.end_points

import com.khaled.savingmoney.network.response.account.AccountListResponse
import com.khaled.savingmoney.network.response.budget.BudgetListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoneyApi {
    @GET("budgets")
    suspend fun getBudgetList(): Response<BudgetListResponse>

    @GET("budgets/{access_token}/accounts")
    suspend fun getAccountList(@Path("access_token") accessToken: String): Response<AccountListResponse>
}