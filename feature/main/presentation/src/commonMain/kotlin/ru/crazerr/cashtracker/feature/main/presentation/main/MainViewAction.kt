package ru.crazerr.cashtracker.feature.main.presentation.main

sealed interface MainViewAction {
    data object ManageTransactionDialog : MainViewAction
    data object ManageBudgetDialog : MainViewAction
    data object ManageGoalDialog : MainViewAction
    data object ManageAccountDialog : MainViewAction
    data object PreviousMonthButtonClick : MainViewAction
    data object NextMonthButtonClick : MainViewAction
    data class SetTransactionsToShow(val transactions: Int) : MainViewAction
    data object ManageTransactionsToShowDropdownMenu : MainViewAction
    data class AccountClick(val accountId: Long) : MainViewAction
}
