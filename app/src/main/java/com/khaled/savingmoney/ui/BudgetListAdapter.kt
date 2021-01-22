package com.khaled.savingmoney.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khaled.savingmoney.databinding.ListItemBudgetBinding
import com.khaled.savingmoney.model.Budget
import com.khaled.savingmoney.model.BudgetDiffCallback

class BudgetListAdapter(context: Context) :
    ListAdapter<Budget, BudgetListAdapter.BudgetViewHolder>(BudgetDiffCallback()) {
    private var viewModel = ViewModelProvider((context as BudgetListActivity)).get(BudgetListViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val binding = ListItemBudgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {

    }

    inner class BudgetViewHolder(binding: ListItemBudgetBinding) : RecyclerView.ViewHolder(binding.root) {
//        val thumbnailImageView = binding.thumbnailImageView
//        val titleTextView = binding.titleTextView
//        val authorTextView = binding.authorTextView
//        val dateTextView = binding.dateTextView
    }


}