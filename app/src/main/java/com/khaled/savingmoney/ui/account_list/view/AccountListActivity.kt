package com.khaled.savingmoney.ui.account_list.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.ActivityAccountListBinding
import com.khaled.savingmoney.ui.account_list.adapter.AccountListAdapter
import com.khaled.savingmoney.ui.account_list.view_model.AccountListViewModel
import com.khaled.savingmoney.ui.budget_list.view_model.BudgetListViewModel

class AccountListActivity : AppCompatActivity() {

    private lateinit var accountListAdapter: AccountListAdapter
    private lateinit var binding: ActivityAccountListBinding
    private lateinit var viewModel: AccountListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_list)
    }
}