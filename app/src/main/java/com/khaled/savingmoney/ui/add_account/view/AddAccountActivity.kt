package com.khaled.savingmoney.ui.add_account.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khaled.savingmoney.R
import com.khaled.savingmoney.constant.Constants
import com.khaled.savingmoney.databinding.ActivityAddAccountBinding
import com.khaled.savingmoney.ui.add_account.view_model.AddAccountViewModel

class AddAccountActivity : AppCompatActivity() {

    private var accountTypeBottomSheet: AccountTypeBottomSheet? = null
    private lateinit var binding: ActivityAddAccountBinding
    private lateinit var viewModel: AddAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_account)
        viewModel = ViewModelProvider(this).get(AddAccountViewModel::class.java)
        viewModel.budgetId = intent.getStringExtra(Constants.INTENT_BUDGET_ID_KEY)

        setupObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.backArrowImageView.setOnClickListener { finish() }
        binding.addButton.setOnClickListener { viewModel.onAddButtonClicked() }
        setAccountNameTextWatch()
        setAccountBalanceTextWatch()
        binding.accountTypeTextView.setOnClickListener {
            viewModel.onAccountTypeClicked()
        }
    }

    private fun setAccountBalanceTextWatch() {
        binding.accountBalanceEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onAccountBalanceChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setAccountNameTextWatch() {
        binding.accountNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onAccountNameChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setupObservers() {
        viewModel.showMessage.observe(this) {
            hideProgressBar()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.addButtonState.observe(this, { isEnabled ->
            if (isEnabled == true) {
                setButtonEnabled()
            } else {
                setButtonDisabled()
            }
        })
        viewModel.showAccountTypesBottomSheet.observe(this, { show ->
            if (show == true) {
                accountTypeBottomSheet = AccountTypeBottomSheet()
                accountTypeBottomSheet?.show(supportFragmentManager, "")
            } else {
                accountTypeBottomSheet?.dismiss()
                binding.accountTypeTextView.text = viewModel.account.type
            }
        })
        viewModel.showSuccessfullyMessageLiveData.observe(this, {
            Toast.makeText(this, getString(R.string.account_successfully_added), Toast.LENGTH_LONG).show()
            finish()
        })

    }

    private fun setButtonEnabled() {
        binding.addButton.isEnabled = true
        binding.addButton.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
    }

    private fun setButtonDisabled() {
        binding.addButton.isEnabled = false
        binding.addButton.setTextColor(ContextCompat.getColor(this, R.color.text_hint))
    }

    private fun hideProgressBar() {

    }
}