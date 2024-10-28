package ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory

sealed interface BudgetsStoryComponentAction {
    data object BackClick : BudgetsStoryComponentAction
}
