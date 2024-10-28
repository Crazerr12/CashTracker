package ru.crazerr.cashtracker.feature.budgets.presentation.budgets

sealed interface BudgetsViewAction {
    data object AddNewBudgetClick : BudgetsViewAction
    data class EditBudgetClick(val id: Long) : BudgetsViewAction
    data class DeleteBudgetClick(val id: Long) : BudgetsViewAction
}
