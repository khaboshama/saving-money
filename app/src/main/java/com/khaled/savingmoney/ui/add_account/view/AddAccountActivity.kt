package com.khaled.savingmoney.ui.add_account.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khaled.savingmoney.R
import com.khaled.savingmoney.databinding.ActivityAccountListBinding
import com.khaled.savingmoney.databinding.ActivityAddAccountBinding
import com.khaled.savingmoney.ui.account_list.view_model.AccountListViewModel
import com.khaled.savingmoney.ui.add_account.view_model.AddAccountViewModel

class AddAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAccountBinding
    private lateinit var viewModel: AddAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_account)
        viewModel = ViewModelProvider(this).get(AddAccountViewModel::class.java)
        setupObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.backArrowImageView.setOnClickListener { finish() }
        binding.addButton.setOnClickListener { viewModel.onAddButtonClicked() }
    }

    private fun setupObservers() {
        viewModel.accountList.observe(this) {
            hideProgressBar()
        }
        viewModel.showMessage.observe(this) {
            hideProgressBar()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun hideProgressBar() {

    }
}