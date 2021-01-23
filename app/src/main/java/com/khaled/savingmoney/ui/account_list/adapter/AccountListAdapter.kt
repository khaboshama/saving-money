package com.khaled.savingmoney.ui.account_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.ListItemAccountBinding
import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.model.account.AccountDiffCallback
import com.khaled.savingmoney.ui.account_list.view.AccountListActivity
import com.khaled.savingmoney.ui.budget_list.view_model.BudgetListViewModel

class AccountListAdapter(context: Context) :
    ListAdapter<Account, AccountListAdapter.AccountViewHolder>(AccountDiffCallback()) {

    private var viewModel = ViewModelProvider((context as AccountListActivity)).get(BudgetListViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = ListItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = getItem(position)
        holder.nameTextView.text = account.name
        holder.typeTextView.text = String.format(holder.itemView.context.getString(R.string.type_format), account.type)
        holder.balanceTextView.text =
            String.format(holder.itemView.context.getString(R.string.balance_format), account.balance.toString())
    }

    inner class AccountViewHolder(binding: ListItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.nameTextView
        val typeTextView = binding.typeTextView
        val balanceTextView = binding.balanceTextView
    }
}