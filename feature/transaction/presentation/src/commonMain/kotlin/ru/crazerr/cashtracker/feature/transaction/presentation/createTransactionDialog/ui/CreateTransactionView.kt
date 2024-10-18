package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import cashtracker.feature.transaction.presentation.generated.resources.Res
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_account_hint
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_amount_hint
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_category_hint
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_date_hint
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_description_hint
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_name_hint
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_title
import cashtracker.feature.transaction.presentation.generated.resources.create_transaction_dialog_type_text
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ru.crazerr.cashtracker.core.compose.components.AppDialog
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.ButtonsFooter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.dateTime.toEpochMilliSeconds
import ru.crazerr.cashtracker.core.utils.dateTime.toLocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.core.utils.visualTransformation.CurrencySeparatorVisualTransformation
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewFactory
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionState
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewAction

@Composable
internal fun CreateTransactionView(
    modifier: Modifier = Modifier,
    component: CreateTransactionComponent,
) {
    val state by component.state.subscribeAsState()
    val dialogState by component.dialog.subscribeAsState()

    dialogState.child?.instance?.also {
        when (it) {
            is CreateTransactionComponent.DialogChild.CreateAccountDialog -> {
                val createAccountViewFactory = koinInject<CreateAccountViewFactory>()
                createAccountViewFactory.create(modifier = Modifier, component = it.component)
            }

            is CreateTransactionComponent.DialogChild.CreateCategoryDialog -> {
                val createCategoryViewFactory = koinInject<CreateCategoryViewFactory>()
                createCategoryViewFactory.create(modifier = Modifier, component = it.component)
            }
        }
    }

    AppDialog(
        modifier = modifier,
        onDismissRequest = { component.obtainViewAction(CreateTransactionViewAction.CancelClick) },
        title = {
            BasicText(
                text = stringResource(Res.string.create_transaction_dialog_title),
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )
        },
        footer = {
            ButtonsFooter(
                modifier = Modifier.align(Alignment.End),
                onCancelClick = { component.obtainViewAction(CreateTransactionViewAction.CancelClick) },
                onSaveClick = { component.obtainViewAction(CreateTransactionViewAction.SaveTransactionClick) }
            )
        }
    ) { contentModifier ->
        CreateTransactionViewContent(
            modifier = Modifier.then(contentModifier),
            state = state,
            obtainViewAction = component::obtainViewAction
        )
    }
}

@Composable
private fun CreateTransactionViewContent(
    modifier: Modifier = Modifier,
    state: CreateTransactionState,
    obtainViewAction: (CreateTransactionViewAction) -> Unit,
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        AppTextField(
            value = state.name,
            onValueChange = { obtainViewAction(CreateTransactionViewAction.NameChange(it)) },
            hint = stringResource(Res.string.create_transaction_dialog_name_hint),
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        BasicText(
            text = stringResource(Res.string.create_transaction_dialog_type_text),
            style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

        GroupRow(state = state, obtainViewAction = obtainViewAction)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        AppTextField(
            value = state.amount,
            error = state.amountError,
            visualTransformation = if (state.amount.isEmpty()) {
                VisualTransformation.None
            } else {
                CurrencySeparatorVisualTransformation(state.selectedAccount.currency.symbolNative)
            },
            onValueChange = { obtainViewAction(CreateTransactionViewAction.AmountChange(it)) },
            hint = stringResource(Res.string.create_transaction_dialog_amount_hint)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        DropdownRow(
            label = stringResource(Res.string.create_transaction_dialog_category_hint),
            selectedItem = state.selectedCategory.name,
            items = state.categories,
            isExpanded = state.categoriesIsExpand,
            onItemSelected = { obtainViewAction(CreateTransactionViewAction.SelectCategory(it)) },
            onManageClick = { obtainViewAction(CreateTransactionViewAction.ManageCategoriesDropdownMenu) },
            hint = stringResource(Res.string.create_transaction_dialog_category_hint),
            onNewClick = { obtainViewAction(CreateTransactionViewAction.CreateNewCategory) }
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        DateRow(state = state, obtainViewAction = obtainViewAction)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        DropdownRow(
            label = stringResource(Res.string.create_transaction_dialog_account_hint),
            selectedItem = state.selectedAccount.name,
            items = state.accounts,
            isExpanded = state.accountsIsExpand,
            onItemSelected = { obtainViewAction(CreateTransactionViewAction.SelectAccount(it)) },
            onManageClick = { obtainViewAction(CreateTransactionViewAction.ManageAccountsDropdownMenu) },
            hint = stringResource(Res.string.create_transaction_dialog_account_hint),
            onNewClick = { obtainViewAction(CreateTransactionViewAction.CreateNewAccount) }
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        AppTextField(
            value = state.description ?: "",
            onValueChange = { obtainViewAction(CreateTransactionViewAction.DescriptionChange(it)) },
            hint = stringResource(Res.string.create_transaction_dialog_description_hint)
        )
    }
}

@Suppress("LongParameterList")
@Composable
private fun <T> DropdownRow(
    modifier: Modifier = Modifier,
    label: String,
    hint: String,
    selectedItem: String,
    items: List<T>,
    isExpanded: Boolean,
    onItemSelected: (T) -> Unit,
    onManageClick: () -> Unit,
    onNewClick: () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            AppTextField(
                modifier = Modifier.clickable { onManageClick() },
                value = selectedItem.ifEmpty { label },
                onValueChange = {},
                hint = hint,
                enabled = false,
            )

            DropdownMenu(
                modifier = Modifier,
                expanded = isExpanded,
                onDismissRequest = onManageClick
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            BasicText(
                                text = if (item is Category) item.name else if (item is Account) item.name else "",
                                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
                            )
                        },
                        onClick = { onItemSelected(item) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        AppIconButton(
            iconTint = AppTheme.Colors.black,
            icon = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            onClick = onNewClick
        )
    }
}

@Composable
private fun GroupButtonWithText(
    modifier: Modifier = Modifier,
    rowTransactionType: TransactionType,
    currentTransactionType: TransactionType,
    setTransactionType: () -> Unit,
) {
    Row(
        modifier = modifier.selectable(
            selected = currentTransactionType == rowTransactionType,
            onClick = setTransactionType
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = currentTransactionType == rowTransactionType,
            onClick = { },
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

        BasicText(
            text = rowTransactionType.name,
            style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))
    }
}

@Composable
private fun GroupRow(
    modifier: Modifier = Modifier,
    state: CreateTransactionState,
    obtainViewAction: (CreateTransactionViewAction) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth().selectableGroup()) {
        GroupButtonWithText(
            rowTransactionType = TransactionType.EXPENSE,
            currentTransactionType = state.transactionType,
            setTransactionType = {
                obtainViewAction(
                    CreateTransactionViewAction.SetTransactionType(
                        TransactionType.EXPENSE
                    )
                )
            }
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

        GroupButtonWithText(
            rowTransactionType = TransactionType.INCOME,
            currentTransactionType = state.transactionType,
            setTransactionType = {
                obtainViewAction(
                    CreateTransactionViewAction.SetTransactionType(
                        TransactionType.INCOME
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateRow(
    modifier: Modifier = Modifier,
    state: CreateTransactionState,
    obtainViewAction: (CreateTransactionViewAction) -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = state.date.toEpochMilliSeconds()
    )

    LaunchedEffect(datePickerState.selectedDateMillis) {
        if (datePickerState.selectedDateMillis != null) {
            obtainViewAction(
                CreateTransactionViewAction.SetDate(
                    date = datePickerState.selectedDateMillis!!.toLocalDate()
                )
            )
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        AppTextField(
            value = state.dateString,
            placeholder = "DD.MM.YYYY",
            onValueChange = { obtainViewAction(CreateTransactionViewAction.UpdateDateString(it)) },
            hint = stringResource(Res.string.create_transaction_dialog_date_hint),
            trailingIcon = {
                AppIconButton(
                    iconTint = AppTheme.Colors.black,
                    icon = AppIcons.Calendar.painter,
                    contentDescription = AppIcons.Calendar.contentDescription,
                    onClick = { obtainViewAction(CreateTransactionViewAction.ManageDatePicker) }
                )
            }
        )

        if (state.showDatePicker) {
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
