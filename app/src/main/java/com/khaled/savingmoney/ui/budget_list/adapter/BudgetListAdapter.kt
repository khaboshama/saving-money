package com.khaled.savingmoney.ui.budget_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.ListItemBudgetBinding
import com.khaled.savingmoney.model.budget.Budget
import com.khaled.savingmoney.model.budget.BudgetDiffCallback
import com.khaled.savingmoney.ui.budget_list.view_model.BudgetListViewModel
import com.khaled.savingmoney.ui.budget_list.view.BudgetListActivity
import com.khaled.savingmoney.utils.DateUtils

class BudgetListAdapter(context: Context) :
    ListAdapter<Budget, BudgetListAdapter.BudgetViewHolder>(BudgetDiffCallback()) {
    private var viewModel = ViewModelProvider((context as BudgetListActivity)).get(BudgetListViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val binding = ListItemBudgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val budget = getItem(position)
        holder.nameTextView.text = budget.name
        holder.firstMonthTextView.text =
            String.format(holder.itemView.context.getString(R.string.first_month), budget.firstMonth)
        holder.lastMonthTextView.text =
            String.format(holder.itemView.context.getString(R.string.last_month), budget.lastMonth)

        holder.lastModifiedDateTextView.text =
            String.format(
                holder.itemView.context.getString(R.string.date_format),
                DateUtils.getDateFormat(budget.modifiedDate, budget.dateFormat?.format)
            )
        holder.itemView.setOnClickListener{
            viewModel.onBudgetClicked(budget)
        }
    }

    inner class BudgetViewHolder(binding: ListItemBudgetBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.budgetNameTextView
        val lastModifiedDateTextView = binding.lastModifiedOnTextView
        val firstMonthTextView = binding.firstMonthTextView
        val lastMonthTextView = binding.lastMonthTextView
    }
}