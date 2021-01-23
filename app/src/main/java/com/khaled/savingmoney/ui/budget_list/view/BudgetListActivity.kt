package com.khaled.savingmoney.ui.budget_list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khaled.savingmoney.R
import com.khaled.savingmoney.constant.Constants
import com.khaled.savingmoney.databinding.ActivityBudgetListBinding
import com.khaled.savingmoney.ui.account_list.view.AccountListActivity
import com.khaled.savingmoney.ui.budget_list.adapter.BudgetListAdapter
import com.khaled.savingmoney.ui.budget_list.view_model.BudgetListViewModel

class BudgetListActivity : AppCompatActivity() {

    private lateinit var budgetListAdapter: BudgetListAdapter
    private lateinit var binding: ActivityBudgetListBinding
    private lateinit var viewModel: BudgetListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_budget_list)
        viewModel = ViewModelProvider(this).get(BudgetListViewModel::class.java)
        setupBudgetListRecyclerView()
        setupObservers()
        viewModel.loadBudgetList()
    }

    private fun setupObservers() {
        viewModel.budgetList.observe(this) {
            hideProgressBar()
            budgetListAdapter.submitList(it)
        }
        viewModel.showMessage.observe(this) {
            hideProgressBar()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.navigateToBudgetScreenLiveData.observe(this) { budget ->
            startActivity(Intent(this, AccountListActivity::class.java).apply {
                putExtra(Constants.INTENT_BUDGET_KEY, budget)
            })
        }
    }

    private fun hideProgressBar() {
        binding.loadingProgressBar.visibility = View.GONE
    }

    private fun setupBudgetListRecyclerView() {
        budgetListAdapter = BudgetListAdapter(this)
        binding.budgetsRecyclerView.adapter = budgetListAdapter
    }
}