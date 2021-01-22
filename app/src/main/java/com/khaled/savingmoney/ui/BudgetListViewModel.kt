package com.khaled.savingmoney.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled.savingmoney.R
import com.khaled.savingmoney.model.Budget
import com.khaled.savingmoney.network.RetrofitService
import com.khaled.savingmoney.network.response.BudgetListResponse
import com.khaled.savingmoney.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BudgetListViewModel(application: Application) : AndroidViewModel(application) {

    var budgetList = MutableLiveData<List<Budget>>()
        private set

    var navigateToBudgetScreenLiveData = SingleLiveEvent<Budget>()
        private set

    var showMessage = MutableLiveData<String>()
        private set

    fun loadBudgetList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val budgetListResponse = RetrofitService.moneyServiceApi.getBudgetsList()
                    if (budgetListResponse.isSuccessful) {
                        parseArticleListSuccessResponse(budgetListResponse)
                    } else {
                        parseArticleListErrorResponse()
                    }
                } catch (e: Exception) {
                    parseArticleListErrorResponse()
                }
            }
        }
    }

    private suspend fun parseArticleListSuccessResponse(response: Response<BudgetListResponse>) {
        withContext(Dispatchers.Main) {
            budgetList.value = response.body()?.dataBudgetList?.budgetList
        }
    }

    private suspend fun parseArticleListErrorResponse() {
        withContext(Dispatchers.Main) {
            showMessage.value = getApplication<Application>().getString(R.string.error_message)
        }
    }

}