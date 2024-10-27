package ru.crazerr.cashtracker.feature.presentation.api.newGoal

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.goal.domain.api.model.Priority

data class NewGoalState(
    val name: String,
    val nameError: String?,
    val currentAmount: String,
    val currentAmountError: String?,
    val targetAmount: String,
    val targetAmountError: String?,
    val startDate: LocalDate,
    val startDateString: String,
    val showStartDatePicker: Boolean,
    val finalDate: LocalDate,
    val finalDateString: String,
    val showFinalDatePicker: Boolean,
    val priority: Priority,
    val priorityDropdownIsExpanded: Boolean,
    val description: String?,
)

internal val InitialNewGoalState = NewGoalState(
    name = "",
    nameError = null,
    currentAmount = "",
    currentAmountError = null,
    targetAmount = "",
    targetAmountError = null,
    startDate = LocalDate.now(),
    startDateString = "",
    showStartDatePicker = false,
    finalDate = LocalDate.now(),
    finalDateString = "",
    showFinalDatePicker = false,
    priority = Priority.LOW,
    priorityDropdownIsExpanded = false,
    description = null
)
