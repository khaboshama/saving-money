package com.khaled.savingmoney

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.khaled.savingmoney.model.account.Account
import com.khaled.savingmoney.model.budget.Budget
import com.khaled.savingmoney.ui.budget_list.view.BudgetListActivity
import com.khaled.savingmoney.ui.budget_list.view_model.BudgetListViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BudgetListInstrumentedTest {


    @get:Rule
    var mActivityRule: ActivityTestRule<BudgetListActivity> = ActivityTestRule(BudgetListActivity::class.java)

    private lateinit var viewModel: BudgetListViewModel
    private lateinit var budget: Budget
    private lateinit var account: Account
    private val id = "57e05e74-c6ba-4f23-b67b-e1c7bebd6bec"
    private val budgetName = "budget1"
    private val accountName = "account3"
    private val type = "type: savings"
    private val balance = "balance: 1000"
    private val firstMonth = "First App"
    private val lastMonth = "First App"
    private lateinit var toolbarTitleAccountList: String

    @Before
    fun setup() {
        viewModel = ViewModelProvider(mActivityRule.activity).get(BudgetListViewModel::class.java)
        budget = Budget(id = id, name = budgetName, firstMonth = firstMonth, lastMonth = lastMonth)
        account = Account(name = accountName, type = type, balance = balance)
        toolbarTitleAccountList = viewModel.getApplication<Application>().getString(R.string.account_list)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.khaled.savingmoney", appContext.packageName)
        Thread.sleep(5000)
    }

    @Test
    fun test_recyclerView_is_empty() {
        assertEquals(getBudgetListSize(), 0)
        Thread.sleep(1000)
    }

    @Test
    fun test_recyclerView_is_not_empty() {
        assertEquals(getBudgetListSize(), 0)
        Thread.sleep(5000)
        assertEquals(getBudgetListSize(), 3)
    }

    @Test
    fun test_navigate_to_account_list() {
        viewModel.navigateToAccountListScreenLiveData.postValue(budget)
        Thread.sleep(5000)
        onView(withId(R.id.toolbar_text_view)).check(matches(withText(toolbarTitleAccountList)));
    }

    private fun getBudgetListSize(): Int {
        val recyclerView = mActivityRule.activity.findViewById<View>(R.id.budgets_recycler_view) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}