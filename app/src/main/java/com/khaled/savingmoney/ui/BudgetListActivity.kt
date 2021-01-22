package com.khaled.savingmoney.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.ActivityBudgetListBinding

class BudgetListActivity : AppCompatActivity() {

    private lateinit var articleListAdapter: BudgetListAdapter
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
            articleListAdapter.submitList(it)
        }
        viewModel.showMessage.observe(this) {
            hideProgressBar()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.navigateToBudgetScreenLiveData.observe(this) {
//            startActivity(Intent(this, ArticleDetailsActivity::class.java).apply {
//                putExtra(Constants.INTENT_BUDGET_KEY, it)
//            })
        }
    }

    private fun hideProgressBar() {
        binding.loadingProgressBar.visibility = View.GONE
    }

    private fun setupBudgetListRecyclerView() {
        articleListAdapter = BudgetListAdapter(this)
        binding.articlesRecyclerView.adapter = articleListAdapter
    }
}