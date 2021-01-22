package com.khaled.savingmoney.model.account

import androidx.recyclerview.widget.DiffUtil

class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {

    override fun areItemsTheSame(oldItem: Account, newItem: Account) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Account, newItem: Account) = oldItem == newItem
}