package ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory

sealed interface TransactionsStoryComponentAction {
    data object BackClick : TransactionsStoryComponentAction
}