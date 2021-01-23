package com.khaled.savingmoney.ui.account_list.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled.savingmoney.R
import com.khaled.savingmoney.constant.Constants
import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.network.RetrofitService
import com.khaled.savingmoney.network.response.account.AccountListResponse
import com.khaled.savingmoney.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AccountListViewModel(application: Application) : AndroidViewModel(application) {

    var accountList = MutableLiveData<List<Account>>()
        private set

    var navigateToCreateAccountScreenLiveData = SingleLiveEvent<Void>()
        private set

    var showMessage = MutableLiveData<String>()
        private set

    fun loadAccountList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val accountListResponse = RetrofitService.moneyServiceApi.getAccountList(Constants.ACCESS_TOKEN)
                    if (accountListResponse.isSuccessful) {
                        parseAccountListSuccessResponse(accountListResponse)
                    } else {
                        parseAccountListErrorResponse()
                    }
                } catch (e: Exception) {
                    parseAccountListErrorResponse()
                }
            }
        }
    }

    private suspend fun parseAccountListSuccessResponse(response: Response<AccountListResponse>) {
        withContext(Dispatchers.Main) {
            accountList.value = response.body()?.dataBudgetList?.accountList?.sortedByDescending { it.balance }
        }
    }

    private suspend fun parseAccountListErrorResponse() {
        withContext(Dispatchers.Main) {
            showMessage.value = getApplication<Application>().getString(R.string.error_message)
        }
    }

}