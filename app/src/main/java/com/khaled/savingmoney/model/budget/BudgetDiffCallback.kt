package com.khaled.savingmoney.model.budget

import androidx.recyclerview.widget.DiffUtil

class BudgetDiffCallback : DiffUtil.ItemCallback<Budget>() {

    override fun areItemsTheSame(oldItem: Budget, newItem: Budget) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Budget, newItem: Budget) = oldItem == newItem
}