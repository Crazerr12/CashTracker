package ru.crazerr.cashtracker.feature.goal.presentation.newGoal.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.compose.components.AppDialog
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.ButtonsFooter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.dateTime.toEpochMilliSeconds
import ru.crazerr.cashtracker.core.utils.dateTime.toLocalDate
import ru.crazerr.cashtracker.core.utils.visualTransformation.CurrencySeparatorVisualTransformation
import ru.crazerr.cashtracker.feature.goal.domain.api.model.Priority
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponent
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalState
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalViewAction

@Composable
internal fun NewGoalView(modifier: Modifier = Modifier, component: NewGoalComponent) {
    val state by component.state.subscribeAsState()

    AppDialog(
        modifier = modifier,
        onDismissRequest = { component.obtainViewAction(NewGoalViewAction.CancelClick) },
        title = { BasicText(text = "Создать цель", style = AppTheme.TextStyles.title) },
        footer = {
            ButtonsFooter(
                modifier = Modifier.align(Alignment.End),
                onCancelClick = { component.obtainViewAction(NewGoalViewAction.CancelClick) },
                onSaveClick = { component.obtainViewAction(NewGoalViewAction.SaveClick) }
            )
        }
    ) { contentModifier ->
        NewGoalViewContent(
            modifier = Modifier.then(contentModifier),
            state = state,
            obtainViewAction = component::obtainViewAction
        )
    }
}

@Composable
private fun NewGoalViewContent(
    modifier: Modifier = Modifier,
    state: NewGoalState,
    obtainViewAction: (NewGoalViewAction) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AppTextField(
            value = state.name,
            onValueChange = { obtainViewAction(NewGoalViewAction.UpdateName(it)) },
            hint = "Название"
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        AppTextField(
            value = state.currentAmount,
            error = state.currentAmountError,
            visualTransformation = if (state.currentAmount.isEmpty()) {
                VisualTransformation.None
            } else {
                CurrencySeparatorVisualTransformation("$")
            },
            onValueChange = { obtainViewAction(NewGoalViewAction.UpdateCurrentAmount(it)) },
            hint = "Текущая сумма"
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        AppTextField(
            value = state.targetAmount,
            error = state.targetAmountError,
            visualTransformation = if (state.targetAmount.isEmpty()) {
                VisualTransformation.None
            } else {
                CurrencySeparatorVisualTransformation("$")
            },
            onValueChange = { obtainViewAction(NewGoalViewAction.UpdateTargetAmount(it)) },
            hint = "Планируемая сумма"
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        StartAndFinalDate(state = state, obtainViewAction = obtainViewAction)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        PriorityRow(state = state, obtainViewAction = obtainViewAction)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        AppTextField(
            value = state.description ?: "",
            onValueChange = { obtainViewAction(NewGoalViewAction.UpdateDescription(it)) },
            hint = "Описание"
        )
    }
}

@Composable
private fun PriorityRow(
    modifier: Modifier = Modifier,
    state: NewGoalState,
    obtainViewAction: (NewGoalViewAction) -> Unit,
) {
    Box(modifier = modifier.clickable { obtainViewAction(NewGoalViewAction.ManagePriorityDropdown) }) {
        AppTextField(
            value = state.priority.toString(),
            onValueChange = {},
            hint = "Приоритет",
            enabled = false,
        )

        DropdownMenu(
            expanded = state.priorityDropdownIsExpanded,
            onDismissRequest = { obtainViewAction(NewGoalViewAction.ManagePriorityDropdown) }
        ) {
            (Priority.entries.toTypedArray()).forEach {
                DropdownMenuItem(
                    text = {
                        BasicText(
                            text = it.name.lowercase().replaceFirstChar { it.uppercase() },
                            style = AppTheme.TextStyles.body
                        )
                    },
                    onClick = { obtainViewAction(NewGoalViewAction.UpdatePriority(it)) }
                )
            }
        }
    }
}

@Composable
private fun StartAndFinalDate(
    state: NewGoalState,
    obtainViewAction: (NewGoalViewAction) -> Unit,
) {
    DateRow(
        date = state.startDate,
        setDate = { obtainViewAction(NewGoalViewAction.SetStartDate(it)) },
        dateString = state.startDateString,
        updateDateString = { obtainViewAction(NewGoalViewAction.UpdateStartDateString(it)) },
        hint = "Начальная дата",
        manageDatePicker = { obtainViewAction(NewGoalViewAction.ManageStartDatePicker) },
        showDatePicker = state.showStartDatePicker,
    )

    Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

    DateRow(
        date = state.finalDate,
        setDate = { obtainViewAction(NewGoalViewAction.SetFinalDate(it)) },
        dateString = state.finalDateString,
        updateDateString = { obtainViewAction(NewGoalViewAction.UpdateFinalDateString(it)) },
        hint = "Конечная дата",
        manageDatePicker = { obtainViewAction(NewGoalViewAction.ManageFinalDatePicker) },
        showDatePicker = state.showFinalDatePicker,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateRow(
    modifier: Modifier = Modifier,
    date: LocalDate,
    setDate: (LocalDate) -> Unit,
    dateString: String,
    updateDateString: (String) -> Unit,
    hint: String,
    manageDatePicker: () -> Unit,
    showDatePicker: Boolean,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date.toEpochMilliSeconds()
    )

    LaunchedEffect(datePickerState.selectedDateMillis) {
        if (datePickerState.selectedDateMillis != null) {
            setDate(datePickerState.selectedDateMillis!!.toLocalDate())
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        AppTextField(
            value = dateString,
            placeholder = "DD.MM.YYYY",
            onValueChange = updateDateString,
            hint = hint,
            trailingIcon = {
                AppIconButton(
                    iconTint = AppTheme.Colors.black,
                    icon = AppIcons.Calendar.painter,
                    contentDescription = AppIcons.Calendar.contentDescription,
                    onClick = manageDatePicker
                )
            }
        )

        if (showDatePicker) {
            DatePicker(
                state = datePickerState,
                title = null,
                headline = null,
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    containerColor = AppTheme.Colors.background,
                )
            )
        }
    }
}
