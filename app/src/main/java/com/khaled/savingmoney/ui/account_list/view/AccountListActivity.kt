package com.khaled.savingmoney.ui.account_list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.ActivityAccountListBinding
import com.khaled.savingmoney.ui.account_list.adapter.AccountListAdapter
import com.khaled.savingmoney.ui.account_list.view_model.AccountListViewModel

class AccountListActivity : AppCompatActivity() {

    private lateinit var accountListAdapter: AccountListAdapter
    private lateinit var binding: ActivityAccountListBinding
    private lateinit var viewModel: AccountListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_list)
        viewModel = ViewModelProvider(this).get(AccountListViewModel::class.java)
        setupAccountListRecyclerView()
        setupObservers()
        setListeners()
        viewModel.loadAccountList()
    }

    private fun setListeners() {
        binding.backArrowImageView.setOnClickListener { finish() }
        binding.createAccountButton.setOnClickListener{ viewModel.onCreateAccountButtonClicked() }
    }

    private fun setupObservers() {
        viewModel.accountList.observe(this) {
            hideProgressBar()
            accountListAdapter.submitList(it)
        }
        viewModel.showMessage.observe(this) {
            hideProgressBar()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.navigateToCreateAccountScreenLiveData.observe(this) {

        }
    }

    private fun hideProgressBar() {
        binding.loadingProgressBar.visibility = View.GONE
    }

    private fun setupAccountListRecyclerView() {
        accountListAdapter = AccountListAdapter(this)
        binding.accountsRecyclerView.adapter = accountListAdapter

    }
}