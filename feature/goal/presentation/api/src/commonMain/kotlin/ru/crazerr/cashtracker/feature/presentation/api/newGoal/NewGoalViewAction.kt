package ru.crazerr.cashtracker.feature.presentation.api.newGoal

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.feature.goal.domain.api.model.Priority

sealed interface NewGoalViewAction {
    data object CancelClick : NewGoalViewAction
    data object SaveClick : NewGoalViewAction

    data object ManagePriorityDropdown : NewGoalViewAction
    data object ManageStartDatePicker : NewGoalViewAction
    data object ManageFinalDatePicker : NewGoalViewAction

    data class UpdateStartDateString(val startDate: String) : NewGoalViewAction
    data class UpdateFinalDateString(val finalDate: String) : NewGoalViewAction

    data class UpdateName(val name: String) : NewGoalViewAction
    data class UpdateCurrentAmount(val currentAmount: String) : NewGoalViewAction
    data class UpdateTargetAmount(val targetAmount: String) : NewGoalViewAction
    data class SetStartDate(val startDate: LocalDate) : NewGoalViewAction
    data class SetFinalDate(val finalDate: LocalDate) : NewGoalViewAction
    data class UpdatePriority(val priority: Priority) : NewGoalViewAction
    data class UpdateDescription(val description: String) : NewGoalViewAction
}
