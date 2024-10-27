package ru.crazerr.cashtracker.feature.budget.presentation.newBudget.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.koin.compose.koinInject
import ru.crazerr.cashtracker.core.compose.components.AppDialog
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.ButtonsFooter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.visualTransformation.CurrencySeparatorVisualTransformation
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetState
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetViewAction
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewFactory

@Composable
internal fun NewBudgetView(
    modifier: Modifier = Modifier,
    component: NewBudgetComponent,
) {
    val state by component.state.subscribeAsState()
    val dialogState by component.dialog.subscribeAsState()

    dialogState.child?.instance?.also {
        when (it) {
            is NewBudgetComponent.DialogChild.NewCategoryDialog -> {
                val createCategoryViewFactory = koinInject<CreateCategoryViewFactory>()
                createCategoryViewFactory.create(modifier = Modifier, component = it.component)
            }
        }
    }

    AppDialog(
        modifier = modifier,
        onDismissRequest = { component.obtainViewAction(NewBudgetViewAction.CancelClick) },
        title = {
            BasicText(
                text = "Сделать",
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )
        },
        footer = {
            ButtonsFooter(
                modifier = Modifier.align(Alignment.End),
                onCancelClick = { component.obtainViewAction(NewBudgetViewAction.CancelClick) },
                onSaveClick = { component.obtainViewAction(NewBudgetViewAction.AddNewCategoryClick) }
            )
        }
    ) { contentModifier ->
        NewBudgetViewContent(
            modifier = Modifier.then(contentModifier),
            state = state,
            obtainViewAction = component::obtainViewAction
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewBudgetViewContent(
    modifier: Modifier = Modifier,
    state: NewBudgetState,
    obtainViewAction: (NewBudgetViewAction) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = state.categoriesMenuIsExpanded,
            onExpandedChange = { obtainViewAction(NewBudgetViewAction.ManageCategoryDropdown) },
        ) {
            AppTextField(
                modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable),
                value = state.categorySearch,
                onValueChange = { obtainViewAction(NewBudgetViewAction.UpdateCategorySearch(it)) },
                hint = "Категория",
                trailingIcon = {
                    AppIconButton(
                        icon = AppIcons.PlusIcon.painter,
                        contentDescription = AppIcons.PlusIcon.contentDescription,
                        onClick = { obtainViewAction(NewBudgetViewAction.AddNewCategoryClick) }
                    )
                }
            )

            val foundCategories = state.categories.filter {
                it.name.contains(
                    other = state.categorySearch,
                    ignoreCase = true
                )
            }

            ExposedDropdownMenu(
                expanded = state.categoriesMenuIsExpanded,
                onDismissRequest = { obtainViewAction(NewBudgetViewAction.ManageCategoryDropdown) }
            ) {
                foundCategories.forEach { category ->
                    DropdownMenuItem(
                        onClick = { obtainViewAction(NewBudgetViewAction.CategorySelect(category = category)) },
                        text = { BasicText(text = category.name, style = AppTheme.TextStyles.body) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        AppTextField(
            value = state.maxAmount,
            error = state.maxAmountError,
            visualTransformation = if (state.maxAmount.isEmpty()) {
                VisualTransformation.None
            } else {
                CurrencySeparatorVisualTransformation("$")
            },
            onValueChange = { obtainViewAction(NewBudgetViewAction.AmountChange(it)) },
            hint = "Сумма для бюджета",
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        RegularRow(
            isRegular = state.isRegular,
            onCheck = { obtainViewAction(NewBudgetViewAction.RegularCheckboxClick) }
        )
    }
}

@Composable
private fun RegularRow(modifier: Modifier = Modifier, isRegular: Boolean, onCheck: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isRegular,
            onCheckedChange = { onCheck() }
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        BasicText(
            text = "Сделать регулярной"
        )
    }
}
