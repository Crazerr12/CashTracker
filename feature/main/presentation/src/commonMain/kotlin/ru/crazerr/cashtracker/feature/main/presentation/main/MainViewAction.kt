package ru.crazerr.cashtracker.feature.main.presentation.main

sealed interface MainViewAction {
    data object NewTransactionButtonClick : MainViewAction
    data object PreviousButtonClick : MainViewAction
    data object NextButtonClick : MainViewAction
    data class SetItemsToShow(val itemsToShow: Int) : MainViewAction
    data object ManageDropdownMenu : MainViewAction
}