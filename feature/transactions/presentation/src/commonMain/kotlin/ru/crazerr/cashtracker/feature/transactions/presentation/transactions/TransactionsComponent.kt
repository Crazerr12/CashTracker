package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.core.utils.presentation.throttleLatest
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentAction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary.GetSummaryInfoUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.handler.GetAccountsResultHandler
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.handler.GetCategoriesResultHandler
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.handler.GetSummaryInfoResultHandler
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

internal typealias TransactionsStateHolder = StateHolder<TransactionsState, TransactionsViewAction>

class TransactionsComponent(
    componentContext: ComponentContext,
    private val dependencies: TransactionsDependencies,
    private val onAction: (TransactionsComponentAction) -> Unit,
) : TransactionsStateHolder(InitialTransactionsState), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()
    private val dialogNavigation = SlotNavigation<DialogConfig>()

    private val searchQueryThrottledLambda = throttleLatest(
        coroutineScope = coroutineScope,
        destinationFunction = ::getTransactions
    )

    private val categoryFilterThrottledLambda = throttleLatest(
        coroutineScope = coroutineScope,
        destinationFunction = ::getTransactions
    )

    private val accountFilterThrottledLambda = throttleLatest(
        coroutineScope = coroutineScope,
        destinationFunction = ::getTransactions
    )

    private val startDateFilterThrottled = throttleLatest(
        coroutineScope = coroutineScope,
        destinationFunction = ::getTransactions
    )

    private val endDateFilterThrottled = throttleLatest(
        coroutineScope = coroutineScope,
        destinationFunction = ::getTransactions
    )

    val dialog: Value<ChildSlot<*, DialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::dialogChild
    )

    init {
        doOnCreate {
            coroutineScope.launch {
                val categoriesResult =
                    async(Dispatchers.IO) { dependencies.getCategoriesUseCase.execute(Unit) }
                val accountsResult =
                    async(Dispatchers.IO) { dependencies.getAccountsUseCase.execute(Unit) }

                GetCategoriesResultHandler(
                    result = categoriesResult.await(),
                    delegate = this@TransactionsComponent,
                ).handle()

                GetAccountsResultHandler(
                    result = accountsResult.await(),
                    delegate = this@TransactionsComponent,
                ).handle()

                getTransactions()
            }
        }
    }

    override fun obtainViewAction(action: TransactionsViewAction) {
        when (action) {
            TransactionsViewAction.CreateNewTransactionClick -> onCreateNewTransactionClick()
            is TransactionsViewAction.SearchChange -> onSearchChange(query = action.query)
            is TransactionsViewAction.SelectTab -> onSelectTab(selectedTab = action.tab)
            is TransactionsViewAction.SetStartDateFilter -> onSetStartDateFilter(startDate = action.date)
            is TransactionsViewAction.SetEndDateFilter -> onSetEndDateFilter(endDate = action.date)
            is TransactionsViewAction.TransactionClick -> onTransactionClick(id = action.transactionId)
            is TransactionsViewAction.ManageTransactionHeader -> onManageTransactionHeader(header = action.header)
            TransactionsViewAction.ManageAccountsFilterDropdown -> onManageAccountsFilterDropdown()
            TransactionsViewAction.ManageCategoriesFilterDropdown -> onManageCategoriesFilterDropdown()
            TransactionsViewAction.ManageDateFilterDropdown -> onManageDateFilterDropdown()
            is TransactionsViewAction.AccountCheckboxClick -> onAccountCheckboxClick(account = action.account)
            is TransactionsViewAction.AllAccountsCheckboxClick -> onAllAccountsCheckboxClick(
                isAllChecked = action.isAllChecked
            )

            is TransactionsViewAction.AllCategoriesCheckboxClick -> onAllCategoriesCheckboxClick(
                isAllChecked = action.isAllChecked
            )

            is TransactionsViewAction.CategoryCheckboxClick -> onCategoryCheckboxClick(
                category = action.category
            )
        }
    }

    private fun dialogChild(config: DialogConfig, componentContext: ComponentContext): DialogChild =
        when (config) {
            DialogConfig.CreateTransaction -> createTransactionDialog(componentContext = componentContext)
        }

    private fun createTransactionDialog(componentContext: ComponentContext): DialogChild.CreateTransactionDialog =
        DialogChild.CreateTransactionDialog(
            component = dependencies.createTransactionsComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        CreateTransactionComponentAction.Canceled -> dialogNavigation.dismiss()
                        is CreateTransactionComponentAction.TransactionCreated -> {
                            dialogNavigation.dismiss()
                            getTransactions()
                        }
                    }
                }
            )
        )

    private fun getSummaryInfo() {
        coroutineScope.launch {
            val result =
                dependencies.getSummaryInfoUseCase.execute(
                    params = GetSummaryInfoUseCase.Param(
                        startDate = state.value.startDateFilter,
                        endDate = state.value.endDateFilter,
                        query = state.value.query,
                        accountIds = state.value.accountsFilter.filter { it.value }.map { it.key.id },
                        categoryIds = state.value.categoriesFilter.filter { it.value }
                            .map { it.key.id },
                    )
                )

            GetSummaryInfoResultHandler(
                result = result,
                delegate = this@TransactionsComponent,
                coroutineScope = coroutineScope
            ).handle()
        }
    }

    private fun onAccountCheckboxClick(account: Account) {
        val updatedAccounts = state.value.accountsFilter.toMutableMap()
        updatedAccounts[account] = !updatedAccounts.getValue(account)

        reduceState {
            copy(
                accountsFilter = updatedAccounts,
                allAccountsChecked = !updatedAccounts.values.any { !it }
            )
        }
        accountFilterThrottledLambda()
    }

    private fun onCategoryCheckboxClick(category: Category) {
        val updatedCategories = state.value.categoriesFilter.toMutableMap()
        updatedCategories[category] = !updatedCategories.getValue(category)

        reduceState {
            copy(
                categoriesFilter = updatedCategories,
                allCategoriesChecked = !updatedCategories.values.any { !it }
            )
        }
        categoryFilterThrottledLambda()
    }

    private fun onAllCategoriesCheckboxClick(isAllChecked: Boolean) {
        reduceState {
            copy(
                categoriesFilter = categoriesFilter.mapValues { !isAllChecked },
                allCategoriesChecked = !isAllChecked
            )
        }
        categoryFilterThrottledLambda()
    }

    private fun onAllAccountsCheckboxClick(isAllChecked: Boolean) {
        reduceState {
            copy(
                accountsFilter = accountsFilter.mapValues { !isAllChecked },
                allAccountsChecked = !isAllChecked
            )
        }
        accountFilterThrottledLambda()
    }

    private fun onCreateNewTransactionClick() {
        dialogNavigation.activate(DialogConfig.CreateTransaction)
    }

    private fun onManageDateFilterDropdown() {
        reduceState { copy(dateFilterIsExpanded = !dateFilterIsExpanded) }
    }

    private fun onManageCategoriesFilterDropdown() {
        reduceState { copy(categoriesFilterIsExpanded = !categoriesFilterIsExpanded) }
    }

    private fun onManageAccountsFilterDropdown() {
        reduceState { copy(accountsFilterIsExpanded = !accountsFilterIsExpanded) }
    }

    private fun onSearchChange(query: String) {
        reduceState { copy(query = query) }
        searchQueryThrottledLambda()
    }

    private fun onSelectTab(selectedTab: TransactionsTab) {
        reduceState { copy(selectedTab = selectedTab) }
    }

    private fun onSetStartDateFilter(startDate: LocalDate) {
        reduceState { copy(startDateFilter = startDate) }
        startDateFilterThrottled()
    }

    private fun onSetEndDateFilter(endDate: LocalDate) {
        reduceState { copy(endDateFilter = endDate) }
        endDateFilterThrottled()
    }

    private fun onTransactionClick(id: Long) {
        // TODO Сделать переход на экран транзакции
    }

    private fun onManageTransactionHeader(header: TransactionHeader) {
        reduceState {
            copy(
                transactions = state.value.transactions
            )
        }
    }

    private fun getTransactions() {
        coroutineScope.launch {
            val transactions = dependencies.getTransactionsUseCase.execute(
                params = GetTransactionsUseCase.Params(
                    query = state.value.query,
                    startDate = state.value.startDateFilter,
                    endDate = state.value.endDateFilter,
                    categoryIds = state.value.categoriesFilter.filter { it.value }
                        .map { it.key.id },
                    accountIds = state.value.accountsFilter.filter { it.value }.map { it.key.id }
                )
            ).cachedIn(coroutineScope)
            reduceState { copy(transactions = transactions) }
            getSummaryInfo()
        }
    }

    sealed class DialogChild {
        data class CreateTransactionDialog(val component: CreateTransactionComponent) :
            DialogChild()
    }

    @Serializable
    private sealed interface DialogConfig {
        data object CreateTransaction : DialogConfig
    }
}
