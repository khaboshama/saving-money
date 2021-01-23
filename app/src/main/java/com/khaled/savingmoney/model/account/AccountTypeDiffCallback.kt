package com.khaled.savingmoney.model.account

import androidx.recyclerview.widget.DiffUtil

class AccountTypeDiffCallback : DiffUtil.ItemCallback<AccountType>() {

    override fun areItemsTheSame(oldItem: AccountType, newItem: AccountType) = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: AccountType, newItem: AccountType) = oldItem == newItem
}