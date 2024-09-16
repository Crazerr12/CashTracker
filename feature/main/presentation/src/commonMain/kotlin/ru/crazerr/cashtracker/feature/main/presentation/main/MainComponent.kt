package ru.crazerr.cashtracker.feature.main.presentation.main

import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnResume
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import ru.crazerr.cashtracker.core.utils.coroutine.callUseCase
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetAccountsResultHandler
import ru.crazerr.cashtracker.feature.main.presentation.main.handler.GetTransactionsResultHandler

class MainComponent(
    componentContext: ComponentContext,
    private val dependencies: MainDependencies,
    private val onAction: (MainComponentAction) -> Unit,
) : StateHolder<MainState, MainViewAction>(InitialMainState), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()

    init {
        doOnResume {
            getUserTransactions()
            getUserAccounts()
        }
    }

    override fun obtainViewAction(action: MainViewAction) {
        when (action) {
            MainViewAction.NewTransactionButtonClick -> onNewTransactionClick()
            MainViewAction.NextButtonClick -> onNextButtonClick()
            MainViewAction.PreviousButtonClick -> onPreviousButtonClick()
            is MainViewAction.SetItemsToShow -> onSetItemsToShowClick(action.itemsToShow)
            MainViewAction.ManageDropdownMenu -> onManageDropdownMenu()
        }
    }

    private fun getUserTransactions() {
        coroutineScope.launch {
            val result = callUseCase {
                dependencies.getTransactionsUseCase.execute()
            }

            GetTransactionsResultHandler(
                result = result,
                delegate = this@MainComponent
            ).handle()
        }
    }

    private fun getUserAccounts() {
        coroutineScope.launch {
            val result = callUseCase {
                dependencies.getAccountsUseCase.execute()
            }

            GetAccountsResultHandler(
                result = result,
                delegate = this@MainComponent
            )
        }
    }

    private fun onNewTransactionClick() {

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
}