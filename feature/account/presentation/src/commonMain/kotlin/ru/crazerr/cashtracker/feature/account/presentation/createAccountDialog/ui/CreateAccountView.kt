package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import cashtracker.feature.account.presentation.generated.resources.Res
import cashtracker.feature.account.presentation.generated.resources.create_account_dialog_balance_hint
import cashtracker.feature.account.presentation.generated.resources.create_account_dialog_description_hint
import cashtracker.feature.account.presentation.generated.resources.create_account_dialog_name_hint
import cashtracker.feature.account.presentation.generated.resources.create_account_dialog_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.resources.stringResource
import ru.crazerr.cashtracker.core.compose.components.AppDialog
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.ButtonsFooter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.visualTransformation.CurrencySeparatorVisualTransformation
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountState
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewAction

@Composable
internal fun CreateAccountView(modifier: Modifier = Modifier, component: CreateAccountComponent) {
    val state by component.state.subscribeAsState()

    AppDialog(
        modifier = modifier,
        onDismissRequest = { component.obtainViewAction(CreateAccountViewAction.CancelClick) },
        title = {
            BasicText(
                text = stringResource(Res.string.create_account_dialog_title),
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )
        },
        footer = {
            ButtonsFooter(
                modifier = Modifier.align(Alignment.End),
                onCancelClick = { component.obtainViewAction(CreateAccountViewAction.CancelClick) },
                onSaveClick = { component.obtainViewAction(CreateAccountViewAction.SaveClick) }
            )
        }
    ) { contentModifier ->
        CreateAccountViewContent(
            modifier = Modifier.then(contentModifier),
            state = state,
            obtainViewAction = component::obtainViewAction
        )
    }
}

@Composable
private fun CreateAccountViewContent(
    modifier: Modifier = Modifier,
    state: CreateAccountState,
    obtainViewAction: (CreateAccountViewAction) -> Unit,
) {
    Column(modifier = modifier) {
        AppTextField(
            value = state.name,
            onValueChange = { obtainViewAction(CreateAccountViewAction.UpdateName(it)) },
            hint = stringResource(Res.string.create_account_dialog_name_hint),
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        CurrencyRow(state = state, obtainViewAction = obtainViewAction)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        AppTextField(
            value = state.description,
            onValueChange = { obtainViewAction(CreateAccountViewAction.UpdateDescription(it)) },
            hint = stringResource(Res.string.create_account_dialog_description_hint),
        )
    }
}

@Composable
private fun CurrencyRow(
    modifier: Modifier = Modifier,
    state: CreateAccountState,
    obtainViewAction: (CreateAccountViewAction) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            AppTextField(
                modifier = Modifier,
                value = state.balance,
                onValueChange = { obtainViewAction(CreateAccountViewAction.UpdateCurrentAmount(it)) },
                visualTransformation = if (state.balance.isEmpty()) {
                    VisualTransformation.None
                } else {
                    CurrencySeparatorVisualTransformation(state.selectedCurrency.symbolNative)
                },
                error = state.balanceError,
                hint = stringResource(Res.string.create_account_dialog_balance_hint),
            )
        }

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        CurrencySelector(state = state, obtainViewAction = obtainViewAction)
    }
}

@Composable
private fun CurrencySelector(
    modifier: Modifier = Modifier,
    state: CreateAccountState,
    obtainViewAction: (CreateAccountViewAction) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen8))
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = AppTheme.Colors.black
                ),
                shape = RoundedCornerShape(AppTheme.Dimens.dimen8)
            )
            .clickable {
                obtainViewAction(CreateAccountViewAction.ManageCurrencyMenu)
            }
            .padding(
                start = AppTheme.Dimens.dimen8,
                top = AppTheme.Dimens.dimen8,
                bottom = AppTheme.Dimens.dimen8,
                end = AppTheme.Dimens.dimen4
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(start = AppTheme.Dimens.dimen4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                text = state.selectedCurrency.code,
                style = AppTheme.TextStyles.body,
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

            Icon(
                tint = AppTheme.Colors.black,
                painter = if (state.isExpanded) AppIcons.AngleUp.painter else AppIcons.AngleDown.painter,
                contentDescription = if (state.isExpanded) {
                    AppIcons.AngleUp.contentDescription
                } else {
                    AppIcons.AngleDown.contentDescription
                },
            )
        }

        CurrencyDropdown(state = state, obtainViewAction = obtainViewAction)
    }
}

@Composable
private fun CurrencyDropdown(
    modifier: Modifier = Modifier,
    state: CreateAccountState,
    obtainViewAction: (CreateAccountViewAction) -> Unit,
) {
    DropdownMenu(
        modifier = modifier,
        offset = DpOffset(x = (-140).dp, y = 12.dp),
        expanded = state.isExpanded,
        onDismissRequest = {
            obtainViewAction(
                CreateAccountViewAction.ManageCurrencyMenu
            )
        },
    ) {
        state.currencies.forEach {
            DropdownMenuItem(
                text = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BasicText(text = it.name, style = AppTheme.TextStyles.body)

                        Spacer(modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

                        BasicText(text = it.code, style = AppTheme.TextStyles.body)
                    }
                },
                onClick = { obtainViewAction(CreateAccountViewAction.UpdateCurrency(currency = it)) }
            )
        }
    }
}
