package com.khaled.savingmoney.ui.add_account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khaled.savingmoney.databinding.ListItemAccountTypeBinding
import com.khaled.savingmoney.model.account.AccountType
import com.khaled.savingmoney.model.account.AccountTypeDiffCallback
import com.khaled.savingmoney.ui.add_account.view.AddAccountActivity
import com.khaled.savingmoney.ui.add_account.view_model.AddAccountViewModel

class AccountTypeListAdapter(context: Context) :
    ListAdapter<AccountType, AccountTypeListAdapter.AccountTypeViewHolder>(AccountTypeDiffCallback()) {

    private var viewModel = ViewModelProvider((context as AddAccountActivity)).get(AddAccountViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountTypeViewHolder {
        val binding = ListItemAccountTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountTypeViewHolder, position: Int) {
        val accountType = getItem(position)
        holder.typeTextView.text = accountType.name
        if (accountType.isSelected) {
            holder.selectedImageView.visibility = View.VISIBLE
        } else {
            holder.selectedImageView.visibility = View.GONE
        }
        holder.itemView.setOnClickListener { viewModel.onAccountTypeSelected(accountType) }
    }

    inner class AccountTypeViewHolder(binding: ListItemAccountTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        val typeTextView = binding.typeTextView
        val selectedImageView = binding.selectedImageView
    }
}