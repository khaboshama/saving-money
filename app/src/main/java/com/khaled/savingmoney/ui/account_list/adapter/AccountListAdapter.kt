package com.khaled.savingmoney.ui.account_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khaled.savingmoney.databinding.ListItemBudgetBinding
import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.model.account.AccountDiffCallback
import com.khaled.savingmoney.ui.account_list.view.AccountListActivity
import com.khaled.savingmoney.ui.budget_list.view.BudgetListActivity
import com.khaled.savingmoney.ui.budget_list.view_model.BudgetListViewModel

class AccountListAdapter(context: Context) :
    ListAdapter<Account, AccountListAdapter.BudgetViewHolder>(AccountDiffCallback()) {
    private var viewModel = ViewModelProvider((context as AccountListActivity)).get(BudgetListViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val binding = ListItemBudgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val budget = getItem(position)
        holder.nameTextView.text = budget.name
    }

    inner class BudgetViewHolder(binding: ListItemBudgetBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.budgetNameTextView
        val lastModifiedDateTextView = binding.lastModifiedOnTextView
        val firstMonthTextView = binding.firstMonthTextView
        val lastMonthTextView = binding.lastMonthTextView
    }
}