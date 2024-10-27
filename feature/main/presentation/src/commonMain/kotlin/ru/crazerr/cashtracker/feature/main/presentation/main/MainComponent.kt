package ru.crazerr.cashtracker.feature.main.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnResume
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentAction
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentAction
import ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares.GetCategorySharesUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth.GetExpensesAndIncomeByMonthUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetAccountsResultHandler
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetCategorySharesResultHandler
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetExpensesAndIncomeByMonthResultHandler
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetTransactionsResultHandler
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponent
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentAction
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentAction

class MainComponent(
    componentContext: ComponentContext,
    private val dependencies: MainDependencies,
    private val onAction: (MainComponentAction) -> Unit,
) : StateHolder<MainState, MainViewAction>(InitialMainState), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()

    private val dialogNavigation = SlotNavigation<DialogConfig>()

    val dialog: Value<ChildSlot<*, DialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::dialogChild
    )

    init {
        doOnResume {
            getUserTransactions()
            getUserAccounts()
            getCategoryShares()
            getExpensesAndIncomeByMonth()
        }
    }

    override fun obtainViewAction(action: MainViewAction) {
        when (action) {
            MainViewAction.ManageTransactionDialog -> onManageTransactionDialog()
            MainViewAction.NextMonthButtonClick -> onNextMonthButtonClick()
            MainViewAction.PreviousMonthButtonClick -> onPreviousMonthButtonClick()
            is MainViewAction.SetTransactionsToShow -> onSetTransactionsToShowClick(action.transactions)
            MainViewAction.ManageTransactionsToShowDropdownMenu -> onManageTransactionsToShowDropdownMenu()
            is MainViewAction.AccountClick -> {
                // TODO
            }

            MainViewAction.ManageAccountDialog -> onManageAccountDialog()
            MainViewAction.ManageBudgetDialog -> onManageBudgetDialog()
            MainViewAction.ManageGoalDialog -> onManageGoalDialog()
        }
    }

    private fun onManageAccountDialog() {
        dialogNavigation.activate(DialogConfig.CreateAccount)
    }

    private fun onManageBudgetDialog() {
        dialogNavigation.activate(DialogConfig.CreateBudget)
    }

    private fun onManageGoalDialog() {
        dialogNavigation.activate(DialogConfig.CreateGoal)
    }

    private fun getExpensesAndIncomeByMonth() {
        coroutineScope.launch {
            val result = dependencies.getExpensesAndIncomeByMonthUseCase.execute(
                params = GetExpensesAndIncomeByMonthUseCase.Params(date = state.value.currentDate)
            )

            GetExpensesAndIncomeByMonthResultHandler(
                result = result,
                delegate = this@MainComponent,
            ).handle()
        }
    }

    private fun getUserTransactions() {
        coroutineScope.launch {
            val result =
                dependencies.getTransactionsUseCase.execute(
                    GetTransactionsUseCase.Params(
                        date = state.value.currentDate,
                        limit = state.value.transactionsToShow
                    )
                )

            GetTransactionsResultHandler(
                result = result,
                delegate = this@MainComponent
            ).handle()
        }
    }

    private fun getCategoryShares() {
        coroutineScope.launch {
            val result = dependencies.getCategorySharesUseCase.execute(
                params = GetCategorySharesUseCase.Params(date = state.value.currentDate)
            )

            GetCategorySharesResultHandler(
                result = result,
                delegate = this@MainComponent
            ).handle()
        }
    }

    private fun getUserAccounts() {
        coroutineScope.launch {
            val result = dependencies.getAccountsUseCase.execute(Unit)

            GetAccountsResultHandler(
                result = result,
                delegate = this@MainComponent
            ).handle()
        }
    }

    private fun onManageTransactionDialog() {
        dialogNavigation.activate(DialogConfig.CreateTransaction)
    }

    private fun onNextMonthButtonClick() {
        reduceState {
            copy(
                currentDate = currentDate.plus(DatePeriod(months = 1))
            )
        }
    }

    private fun onSetTransactionsToShowClick(transactions: Int) {
        reduceState { copy(transactionsToShow = transactions, itemsToShowIsExpanded = false) }

        getUserTransactions()
    }

    private fun onPreviousMonthButtonClick() {
        reduceState {
            copy(
                currentDate = currentDate.minus(DatePeriod(months = 1))
            )
        }
    }

    private fun onManageTransactionsToShowDropdownMenu() {
        reduceState {
            copy(
                itemsToShowIsExpanded = !itemsToShowIsExpanded
            )
        }
    }

    private fun dialogChild(config: DialogConfig, componentContext: ComponentContext): DialogChild {
        return when (config) {
            DialogConfig.CreateAccount -> createAccountDialog(componentContext = componentContext)
            DialogConfig.CreateTransaction -> createTransactionDialog(componentContext = componentContext)
            DialogConfig.CreateBudget -> createBudgetDialog(componentContext = componentContext)
            DialogConfig.CreateGoal -> createGoalDialog(componentContext = componentContext)
        }
    }

    private fun createBudgetDialog(componentContext: ComponentContext): DialogChild.CreateBudget =
        DialogChild.CreateBudget(
            component = dependencies.newBudgetComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        NewBudgetComponentAction.BudgetCreated -> dialogNavigation.dismiss()
                        NewBudgetComponentAction.Canceled -> dialogNavigation.dismiss()
                    }
                }
            )
        )

    private fun createGoalDialog(componentContext: ComponentContext): DialogChild.CreateGoal =
        DialogChild.CreateGoal(
            component = dependencies.newGoalComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        NewGoalComponentAction.Canceled -> dialogNavigation.dismiss()
                        NewGoalComponentAction.GoalCreated -> dialogNavigation.dismiss()
                    }
                }
            )
        )

    private fun createAccountDialog(componentContext: ComponentContext): DialogChild.CreateAccount =
        DialogChild.CreateAccount(
            component = dependencies.createAccountComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        is CreateAccountComponentAction.AccountCreated -> dialogNavigation.dismiss()
                        CreateAccountComponentAction.Canceled -> dialogNavigation.dismiss()
                    }
                }
            )
        )

    private fun createTransactionDialog(componentContext: ComponentContext): DialogChild.CreateTransaction =
        DialogChild.CreateTransaction(
            component = dependencies.createTransactionComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        CreateTransactionComponentAction.Canceled -> dialogNavigation.dismiss()
                        is CreateTransactionComponentAction.TransactionCreated -> dialogNavigation.dismiss()
                    }
                }
            )
        )

    sealed class DialogChild {
        data class CreateTransaction(val component: CreateTransactionComponent) : DialogChild()
        data class CreateAccount(val component: CreateAccountComponent) : DialogChild()
        data class CreateBudget(val component: NewBudgetComponent) : DialogChild()
        data class CreateGoal(val component: NewGoalComponent) : DialogChild()
    }

    @Serializable
    private sealed interface DialogConfig {
        data object CreateTransaction : DialogConfig
        data object CreateBudget : DialogConfig
        data object CreateGoal : DialogConfig
        data object CreateAccount : DialogConfig
    }
}
