package ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget

sealed interface NewBudgetComponentAction {
    data object Canceled : NewBudgetComponentAction
    data object BudgetCreated : NewBudgetComponentAction
}
