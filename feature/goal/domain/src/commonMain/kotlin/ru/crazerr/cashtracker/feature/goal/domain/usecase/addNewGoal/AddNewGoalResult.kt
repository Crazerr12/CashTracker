package ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal

sealed interface AddNewGoalResult {
    data object Ok : AddNewGoalResult
    data object NetworkError : AddNewGoalResult
    data class UnknownError(val throwable: Throwable) : AddNewGoalResult
}
