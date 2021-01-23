package com.khaled.savingmoney.ui.budget_list.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled.savingmoney.R
import com.khaled.savingmoney.model.budget.Budget
import com.khaled.savingmoney.network.RetrofitService
import com.khaled.savingmoney.network.response.budget.BudgetListResponse
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
                    val budgetListResponse = RetrofitService.moneyServiceApi.getBudgetList()
                    if (budgetListResponse.isSuccessful) {
                        parseBudgetListSuccessResponse(budgetListResponse)
                    } else {
                        parseBudgetListErrorResponse()
                    }
                } catch (e: Exception) {
                    parseBudgetListErrorResponse()
                }
            }
        }
    }

    private suspend fun parseBudgetListSuccessResponse(response: Response<BudgetListResponse>) {
        withContext(Dispatchers.Main) { budgetList.value = response.body()?.dataBudgetList?.budgetList }
    }

    private suspend fun parseBudgetListErrorResponse() {
        withContext(Dispatchers.Main) {
            showMessage.value = getApplication<Application>().getString(R.string.error_message)
        }
    }

    fun onBudgetClicked(budget: Budget) {
        navigateToBudgetScreenLiveData.value = budget
    }

}