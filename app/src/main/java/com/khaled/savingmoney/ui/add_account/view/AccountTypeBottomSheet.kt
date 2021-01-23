package com.khaled.savingmoney.ui.add_account.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.BottomSheetAccountTypeListLayoutBinding
import com.khaled.savingmoney.ui.add_account.adapter.AccountTypeListAdapter
import com.khaled.savingmoney.ui.add_account.view_model.AddAccountViewModel

class AccountTypeBottomSheet : BottomSheetDialogFragment() {
    private var binding: BottomSheetAccountTypeListLayoutBinding? = null
    private var adapter: AccountTypeListAdapter? = null
    private var viewModel: AddAccountViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_account_type_list_layout, container, false)
        viewModel = ViewModelProvider(context as AddAccountActivity).get(AddAccountViewModel::class.java)
        setupRecyclerView()
        setupListeners()
        return binding?.root
    }

    private fun setupListeners() {
        binding?.bottomSheetCloseButton?.setOnClickListener { dismiss() }
    }

    private fun setupRecyclerView() {
        val liveImageLayoutManager = LinearLayoutManager(activity)
        liveImageLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.bottomSheetRecyclerView?.layoutManager = liveImageLayoutManager
        binding?.bottomSheetRecyclerView?.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        adapter = AccountTypeListAdapter(context as Activity)
        binding?.bottomSheetRecyclerView?.adapter = adapter
        adapter?.submitList(viewModel?.getAccountTypeArrayList())
    }
}
