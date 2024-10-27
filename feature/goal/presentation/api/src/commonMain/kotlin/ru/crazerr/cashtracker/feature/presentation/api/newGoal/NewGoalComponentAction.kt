package ru.crazerr.cashtracker.feature.presentation.api.newGoal

sealed interface NewGoalComponentAction {
    data object Canceled : NewGoalComponentAction
    data object GoalCreated : NewGoalComponentAction
}
