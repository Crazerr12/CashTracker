package ru.crazerr.cashtracker.feature.goal.presentation.newGoal.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal.AddNewGoalResult

internal class AddNewGoalResultHandler(
    private val result: AddNewGoalResult,
    private val onAction: () -> Unit,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            AddNewGoalResult.Ok -> onOk()
            AddNewGoalResult.NetworkError -> onNetworkError()
            is AddNewGoalResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk() {
        onAction()
    }
}
