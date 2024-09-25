package ru.crazerr.cashtracker.feature.main.presentation.main

import kotlinx.datetime.LocalDate

sealed interface MainViewAction {
    data object ManageTransactionDialog : MainViewAction
    data class UpdateNewTransactionTitle(val title: String): MainViewAction
    data class UpdateNewTransactionCategory(val id: Long): MainViewAction
    data class UpdateNewTransactionDate(val date: LocalDate): MainViewAction
    data object PreviousButtonClick : MainViewAction
    data object NextButtonClick : MainViewAction
    data class SetItemsToShow(val itemsToShow: Int) : MainViewAction
    data object ManageDropdownMenu : MainViewAction
    data class AccountClick(val accountId: Long): MainViewAction
}