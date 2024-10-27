package ru.crazerr.cashtracker.feature.goal.presentation.newGoal

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.dateTime.fromInput
import ru.crazerr.cashtracker.core.utils.dateTime.toInput
import ru.crazerr.cashtracker.core.utils.toDateFormat
import ru.crazerr.cashtracker.feature.goal.domain.api.model.Priority
import ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal.AddNewGoalUseCase
import ru.crazerr.cashtracker.feature.goal.presentation.newGoal.handler.AddNewGoalResultHandler
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponent
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentAction
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalViewAction

private const val DATE_LENGTH = 10

internal class NewGoalComponentImpl(
    componentContext: ComponentContext,
    private val onAction: (NewGoalComponentAction) -> Unit,
    private val dependencies: NewGoalDependencies,
) : NewGoalComponent(), ComponentContext by componentContext {
    private val coroutineScope = componentCoroutineScope()

    override fun obtainViewAction(action: NewGoalViewAction) {
        when (action) {
            NewGoalViewAction.CancelClick -> onAction(NewGoalComponentAction.Canceled)
            NewGoalViewAction.SaveClick -> onSaveClick()
            is NewGoalViewAction.SetFinalDate -> onSetFinalDate(finalDate = action.finalDate)
            is NewGoalViewAction.SetStartDate -> onSetStartDate(startDate = action.startDate)
            is NewGoalViewAction.UpdateCurrentAmount -> onUpdateCurrentAmount(currentAmount = action.currentAmount)
            is NewGoalViewAction.UpdateDescription -> onUpdateDescription(description = action.description)
            is NewGoalViewAction.UpdateTargetAmount -> onUpdateTargetAmount(targetAmount = action.targetAmount)
            is NewGoalViewAction.UpdateName -> onUpdateName(name = action.name)
            is NewGoalViewAction.UpdatePriority -> onUpdatePriority(priority = action.priority)
            NewGoalViewAction.ManagePriorityDropdown -> onManagePriorityDropdown()
            NewGoalViewAction.ManageFinalDatePicker -> onManageFinalDatePicker()
            NewGoalViewAction.ManageStartDatePicker -> onManageStartDatePicker()
            is NewGoalViewAction.UpdateFinalDateString -> onUpdateFinalDateString(finalDateString = action.finalDate)
            is NewGoalViewAction.UpdateStartDateString -> onUpdateStartDateString(startDateString = action.startDate)
        }
    }

    private fun onUpdateStartDateString(startDateString: String) {
        if (startDateString.all { it.isDigit() || it == '.' } && startDateString.length <= DATE_LENGTH) {
            reduceState { copy(startDateString = startDateString.toDateFormat()) }

            if (startDateString.length == DATE_LENGTH) {
                reduceState { copy(startDate = startDateString.fromInput()) }
            }
        }
    }

    private fun onUpdateFinalDateString(finalDateString: String) {
        if (finalDateString.all { it.isDigit() || it == '.' } && finalDateString.length <= DATE_LENGTH) {
            reduceState { copy(finalDateString = finalDateString.toDateFormat()) }

            if (finalDateString.length == DATE_LENGTH) {
                reduceState { copy(finalDate = finalDateString.fromInput()) }
            }
        }
    }

    private fun onManageStartDatePicker() {
        reduceState { copy(showStartDatePicker = !showStartDatePicker) }
    }

    private fun onManageFinalDatePicker() {
        reduceState { copy(showFinalDatePicker = !showFinalDatePicker) }
    }

    private fun onManagePriorityDropdown() {
        reduceState { copy(priorityDropdownIsExpanded = !priorityDropdownIsExpanded) }
    }

    private fun onSaveClick() {
        coroutineScope.launch {
            val result = dependencies.addNewGoalUseCase.execute(
                params = AddNewGoalUseCase.Params(
                    name = state.value.name,
                    currentAmount = state.value.currentAmount.toFloat(),
                    targetAmount = state.value.targetAmount.toFloat(),
                    startDate = state.value.startDate,
                    finalDate = state.value.finalDate,
                    priority = state.value.priority.priority,
                    description = state.value.description
                )
            )

            AddNewGoalResultHandler(
                result = result,
                onAction = { onAction(NewGoalComponentAction.GoalCreated) }
            ).handle()
        }
    }

    private fun onSetFinalDate(finalDate: LocalDate) {
        reduceState { copy(finalDate = finalDate, finalDateString = finalDate.toInput()) }
    }

    private fun onUpdateName(name: String) {
        reduceState { copy(name = name, nameError = null) }
    }

    private fun onSetStartDate(startDate: LocalDate) {
        reduceState { copy(startDate = startDate, startDateString = startDate.toInput()) }
    }

    private fun onUpdateCurrentAmount(currentAmount: String) {
        reduceState {
            if (currentAmount.lastOrNull()?.isDigit() == true || currentAmount.isEmpty()) {
                copy(currentAmount = currentAmount, currentAmountError = null)
            } else {
                copy(currentAmountError = "Поле принимает только цифры")
            }
        }
    }

    private fun onUpdateDescription(description: String) {
        reduceState { copy(description = description) }
    }

    private fun onUpdatePriority(priority: Priority) {
        reduceState { copy(priority = priority, priorityDropdownIsExpanded = false) }
    }

    private fun onUpdateTargetAmount(targetAmount: String) {
        reduceState {
            if (targetAmount.lastOrNull()?.isDigit() == true || targetAmount.isEmpty()) {
                copy(targetAmount = targetAmount, targetAmountError = null)
            } else {
                copy(targetAmountError = "Поле принимает только цифры")
            }
        }
    }
}
