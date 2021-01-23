package com.khaled.savingmoney.network.end_points

import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.network.response.account.AccountListResponse
import com.khaled.savingmoney.network.response.add_account.AddAccountResponse
import com.khaled.savingmoney.network.response.budget.BudgetListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MoneyApi {
    @GET("budgets")
    suspend fun getBudgetList(): Response<BudgetListResponse>

    @GET("budgets/{budget_id}/accounts")
    suspend fun getAccountList(@Path("budget_id") budgetId: String): Response<AccountListResponse>

    @POST("budgets/{budget_id}/accounts")
    suspend fun addAccount(
        @Body account: Account,
        @Path("budget_id") budgetId: String?,
    ): Response<AddAccountResponse>
}