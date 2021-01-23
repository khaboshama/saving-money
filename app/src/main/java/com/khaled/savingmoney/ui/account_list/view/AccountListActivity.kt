package com.khaled.savingmoney.ui.account_list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khaled.savingmoney.R
import com.khaled.savingmoney.constant.Constants
import com.khaled.savingmoney.databinding.ActivityAccountListBinding
import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.ui.account_list.adapter.AccountListAdapter
import com.khaled.savingmoney.ui.account_list.view_model.AccountListViewModel
import com.khaled.savingmoney.ui.add_account.view.AddAccountActivity

class AccountListActivity : AppCompatActivity() {

    private lateinit var accountListAdapter: AccountListAdapter
    private lateinit var binding: ActivityAccountListBinding
    private lateinit var viewModel: AccountListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_list)
        viewModel = ViewModelProvider(this).get(AccountListViewModel::class.java)
        viewModel.budget = intent.getParcelableExtra(Constants.INTENT_BUDGET_ID_KEY)

        setupAccountListRecyclerView()
        setupObservers()
        setListeners()
        viewModel.loadAccountList()
    }

    private fun setListeners() {
        binding.backArrowImageView.setOnClickListener { finish() }
        binding.createAccountButton.setOnClickListener { viewModel.onCreateAccountButtonClicked() }
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
            startActivityForResult(Intent(this, AddAccountActivity::class.java).apply {
                putExtra(Constants.INTENT_BUDGET_ID_KEY, it)
            }, ADD_ACCOUNT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ADD_ACCOUNT_REQUEST_CODE) {
            val account = data?.getParcelableExtra<Account>(Constants.Intent.ACCOUNT_KEY)
            account?.let { viewModel.addNewAccount(it) }

        }
    }

    private fun hideProgressBar() {
        binding.loadingProgressBar.visibility = View.GONE
    }

    private fun setupAccountListRecyclerView() {
        accountListAdapter = AccountListAdapter(this)
        binding.accountsRecyclerView.adapter = accountListAdapter
    }

    companion object {
        private const val ADD_ACCOUNT_REQUEST_CODE = 1000
    }
}