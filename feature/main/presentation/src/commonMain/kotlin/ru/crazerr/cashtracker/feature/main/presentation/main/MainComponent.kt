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
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentAction
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetAccountsResultHandler
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetTransactionsResultHandler
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
            getUserAccounts()
        }
    }

    override fun obtainViewAction(action: MainViewAction) {
        when (action) {
            MainViewAction.ManageTransactionDialog -> onManageTransactionDialog()
            MainViewAction.NextButtonClick -> onNextButtonClick()
            MainViewAction.PreviousButtonClick -> onPreviousButtonClick()
            is MainViewAction.SetItemsToShow -> onSetItemsToShowClick(action.itemsToShow)
            MainViewAction.ManageDropdownMenu -> onManageDropdownMenu()
            is MainViewAction.UpdateNewTransactionCategory -> onUpdateNewTransactionCategory(action.id)
            is MainViewAction.UpdateNewTransactionDate -> onUpdateNewTransactionDate(action.date)
            is MainViewAction.UpdateNewTransactionTitle -> onUpdateNewTransactionTitle(action.title)
            is MainViewAction.AccountClick -> TODO()
        }
    }

    private fun dialogChild(config: DialogConfig, componentContext: ComponentContext): DialogChild {
        return when (config) {
            DialogConfig.CreateAccount -> createAccountDialog(componentContext = componentContext)
            DialogConfig.CreateTransaction -> createTransactionDialog(componentContext = componentContext)
        }
    }

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

    private fun onUpdateNewTransactionCategory(id: Long) {
        reduceState { copy(newTransactionCategoryId = id) }
    }

    private fun onUpdateNewTransactionDate(date: LocalDate) {
        reduceState { copy(newTransactionDate = date) }
    }

    private fun onUpdateNewTransactionTitle(title: String) {
        reduceState { copy(newTransactionTitle = title) }
    }

    private fun getUserTransactions() {
        coroutineScope.launch {
            val result = dependencies.getTransactionsUseCase.execute()

            GetTransactionsResultHandler(
                result = result,
                delegate = this@MainComponent
            ).handle()
        }
    }

    private fun getUserAccounts() {
        coroutineScope.launch {
            val result = dependencies.getAccountsUseCase.execute()

            GetAccountsResultHandler(
                result = result,
                delegate = this@MainComponent
            ).handle()
        }
    }

    private fun onManageTransactionDialog() {
        dialogNavigation.activate(DialogConfig.CreateTransaction)
    }

    private fun onNextButtonClick() {
        reduceState {
            copy(
                currentDate = currentDate.plus(DatePeriod(months = 1))
            )
        }
    }

    private fun onSetItemsToShowClick(itemsToShow: Int) {
        reduceState { copy(itemsToShow = itemsToShow) }
    }

    private fun onPreviousButtonClick() {
        reduceState {
            copy(
                currentDate = currentDate.minus(DatePeriod(months = 1))
            )
        }
    }

    private fun onManageDropdownMenu() {
        reduceState {
            copy(
                itemsToShowIsExpanded = !itemsToShowIsExpanded
            )
        }
    }

    sealed class DialogChild {
        data class CreateTransaction(val component: CreateTransactionComponent) : DialogChild()
        data class CreateAccount(val component: CreateAccountComponent) : DialogChild()
    }

    @Serializable
    private sealed interface DialogConfig {
        data object CreateTransaction : DialogConfig
        data object CreateAccount : DialogConfig
    }
}