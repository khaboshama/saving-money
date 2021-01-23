package com.khaled.savingmoney.ui.add_account.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled.savingmoney.R
import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.model.account.AccountType
import com.khaled.savingmoney.network.RetrofitService
import com.khaled.savingmoney.network.response.account.AccountListResponse
import com.khaled.savingmoney.network.response.add_account.AddAccountResponse
import com.khaled.savingmoney.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AddAccountViewModel(application: Application) : AndroidViewModel(application) {

    var budgetId: String? = null
    var addButtonState = SingleLiveEvent<Boolean>()
        private set
    var showSuccessfullyMessageLiveData = SingleLiveEvent<Void>()
        private set

    var navigateToCreateAccountScreenLiveData = SingleLiveEvent<Void>()
        private set

    var showMessage = MutableLiveData<String>()
        private set

    var showAccountTypesBottomSheet = SingleLiveEvent<Boolean>()
        private set

    var account = Account()
        private set
    private val accountTypeList = mutableListOf<AccountType>()

    private fun addAccount() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val accountAddedResponse =
                        RetrofitService.moneyServiceApi.addAccount(account, budgetId)
                    if (accountAddedResponse.isSuccessful) {
                        parseAccountSuccessfullyAddedResponse(accountAddedResponse)
                    } else {
                        parseAccountAddedErrorResponse()
                    }
                } catch (e: Exception) {
                    parseAccountAddedErrorResponse()
                }
            }
        }
    }

    private suspend fun parseAccountSuccessfullyAddedResponse(response: Response<AddAccountResponse>) {
        withContext(Dispatchers.Main) {
            showSuccessfullyMessageLiveData.call()
        }
    }

    private suspend fun parseAccountAddedErrorResponse() {
        withContext(Dispatchers.Main) {
            showMessage.value = getApplication<Application>().getString(R.string.error_message)
        }
    }

    fun onCreateAccountButtonClicked() {
        navigateToCreateAccountScreenLiveData.call()
    }

    fun onAddButtonClicked() {
        addAccount()
    }

    fun onAccountNameChanged(name: String) {
        account.name = name
        checkAddButtonState()
    }

    fun onAccountBalanceChanged(balance: String) {
        account.balance = balance
        checkAddButtonState()
    }

    private fun checkAddButtonState() {
        addButtonState.value = account.name.isNullOrBlank().not() &&
                account.balance.isNullOrBlank().not() && account.type.isNullOrBlank().not()
    }

    fun onAccountTypeClicked() {
        showAccountTypesBottomSheet.value = true
    }

    fun onAccountTypeSelected(accountType: AccountType) {
        accountTypeList.find { it.isSelected }?.isSelected = false
        accountTypeList.find { it.name == accountType.name }?.isSelected = true
        account.type = accountType.name
        showAccountTypesBottomSheet.value = false
        checkAddButtonState()
    }

    fun getAccountTypeArrayList(): MutableList<AccountType> {
        if (accountTypeList.isEmpty()) {
            val typeArray =
                getApplication<Application>().resources.getStringArray(R.array.account_types).toMutableList()
            typeArray.forEach { accountTypeList.add(AccountType(it)) }
        }
        return accountTypeList
    }

}