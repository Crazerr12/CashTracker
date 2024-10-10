package ru.crazerr.cashtracker.feature.main.presentation.main.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cashtracker.feature.main.presentation.generated.resources.Res
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_accounts_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_current_balance_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_current_expenses_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_current_income_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_analyze
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_import_data
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_account
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_budget
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_goal
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_transaction
import cashtracker.feature.main.presentation.generated.resources.main_screen_expenses_by_categories_card_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_transactions_card_tab_futures
import cashtracker.feature.main.presentation.generated.resources.main_screen_transactions_card_tab_recently
import cashtracker.feature.main.presentation.generated.resources.main_screen_transactions_card_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ru.crazerr.cashtracker.core.compose.components.AddButton
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.TransactionItem
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.dateTime.getMonthNames
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewFactory
import ru.crazerr.cashtracker.feature.main.domain.model.Account
import ru.crazerr.cashtracker.feature.main.domain.model.Transaction
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent
import ru.crazerr.cashtracker.feature.main.presentation.main.MainState
import ru.crazerr.cashtracker.feature.main.presentation.main.MainViewAction
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewFactory

@Composable
actual fun MainView(component: MainComponent) {
    val state by component.state.subscribeAsState()
    val dialogState by component.dialog.subscribeAsState()

    dialogState.child?.instance?.also {
        when (it) {
            is MainComponent.DialogChild.CreateAccount -> {
                val createAccountDialog = koinInject<CreateAccountViewFactory>()
                createAccountDialog.create(modifier = Modifier, component = it.component)
            }

            is MainComponent.DialogChild.CreateTransaction -> {
                val createTransactionDialog = koinInject<CreateTransactionViewFactory>()
                createTransactionDialog.create(modifier = Modifier, component = it.component)
            }
        }
    }

    MainViewContent(state = state, obtainViewAction = component::obtainViewAction)
}

@Composable
private fun MainViewContent(
    state: MainState,
    obtainViewAction: (MainViewAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(all = AppTheme.Dimens.dimen20)
    ) {
        MainViewHeader(obtainViewAction)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen16))

        ChangeDate(obtainViewAction = obtainViewAction, currentDate = state.currentDate)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen32))

        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().weight(2f, fill = true)) {
                TransactionsCard(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    state = state,
                    obtainViewAction
                )

                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

                CategoryExpensesCard(modifier = Modifier.fillMaxHeight().weight(1f))
            }

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen20))

            BalanceCard(
                modifier = Modifier.fillMaxWidth().weight(1f),
                state = state,
                obtainViewAction = obtainViewAction
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MainViewHeader(obtainViewAction: (MainViewAction) -> Unit) {
    Text(
        text = stringResource(resource = Res.string.main_screen_title),
        style = AppTheme.TextStyles.headlineTitle
    )

    Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen20))

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.Dimens.dimen10),
    ) {
        AddButton(
            text = stringResource(Res.string.main_screen_button_text_new_transaction),
            onClick = { obtainViewAction(MainViewAction.ManageTransactionDialog) },
            shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
            painter = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            background = AppTheme.Colors.blue,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

        AddButton(
            text = stringResource(Res.string.main_screen_button_text_new_budget),
            onClick = { obtainViewAction(MainViewAction.ManageTransactionDialog) },
            shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
            painter = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            background = AppTheme.Colors.green,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

        AddButton(
            text = stringResource(Res.string.main_screen_button_text_new_goal),
            onClick = { obtainViewAction(MainViewAction.ManageTransactionDialog) },
            shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
            painter = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            background = AppTheme.Colors.yellow,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

        AddButton(
            text = stringResource(Res.string.main_screen_button_text_new_account),
            onClick = { obtainViewAction(MainViewAction.ManageTransactionDialog) },
            shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
            painter = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            background = AppTheme.Colors.red,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

        AddButton(
            text = stringResource(Res.string.main_screen_button_text_import_data),
            onClick = { obtainViewAction(MainViewAction.ManageTransactionDialog) },
            shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
            painter = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            background = AppTheme.Colors.blue,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

        AddButton(
            text = stringResource(Res.string.main_screen_button_text_analyze),
            onClick = { obtainViewAction(MainViewAction.ManageTransactionDialog) },
            shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
            painter = AppIcons.PlusIcon.painter,
            contentDescription = AppIcons.PlusIcon.contentDescription,
            background = AppTheme.Colors.blue,
        )
    }
}

@Composable
private fun ChangeDate(
    modifier: Modifier = Modifier,
    obtainViewAction: (MainViewAction) -> Unit,
    currentDate: LocalDate,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AppIconButton(
            icon = AppIcons.ArrowBack.painter,
            onClick = { obtainViewAction(MainViewAction.PreviousButtonClick) },
            contentDescription = AppIcons.ArrowBack.contentDescription,
            iconTint = AppTheme.Colors.background,
            backgroundTint = AppTheme.Colors.black,
        )

        Spacer(modifier = Modifier.width(width = AppTheme.Dimens.dimen10))

        Text(
            text = LocalDate.Format {
                monthName(MonthNames(getMonthNames("ru")))
                char(' ')
                year()
            }.format(currentDate),
            style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.width(width = AppTheme.Dimens.dimen10))

        AppIconButton(
            icon = AppIcons.ArrowForward.painter,
            onClick = { obtainViewAction(MainViewAction.NextButtonClick) },
            contentDescription = AppIcons.ArrowForward.contentDescription,
            iconTint = AppTheme.Colors.black,
            backgroundTint = AppTheme.Colors.white,
        )
    }
}

@Composable
private fun TransactionsCard(
    modifier: Modifier = Modifier,
    state: MainState,
    obtainViewAction: (MainViewAction) -> Unit,
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(Res.string.main_screen_transactions_card_tab_recently),
        stringResource(Res.string.main_screen_transactions_card_tab_futures)
    )

    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(all = AppTheme.Dimens.dimen20)) {
            BasicText(
                text = stringResource(resource = Res.string.main_screen_transactions_card_title),
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen20))

            Row(modifier = Modifier.fillMaxWidth()) {
                TabRow(
                    modifier = Modifier.weight(1f),
                    selectedTabIndex = tabIndex,
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { BasicText(text = title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.weight(1f)
                        .defaultMinSize(minWidth = AppTheme.Dimens.dimen10)
                )

                Box(modifier = Modifier) {
                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = { obtainViewAction(MainViewAction.ManageDropdownMenu) }
                            )
                            .clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen10))
                            .border(
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = AppTheme.Colors.black
                                ),
                                shape = RoundedCornerShape(AppTheme.Dimens.dimen10)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicText(
                            text = state.itemsToShow.toString(),
                            style = AppTheme.TextStyles.body.copy()
                        )

                        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

                        Icon(
                            painter = AppIcons.ArrowBack.painter,
                            contentDescription = AppIcons.ArrowBack.contentDescription
                        )
                    }

                    DropdownMenu(
                        expanded = state.itemsToShowIsExpanded,
                        onDismissRequest = { obtainViewAction(MainViewAction.ManageDropdownMenu) },
                    ) {
                        DropdownMenuItem(
                            text = {
                                BasicText(
                                    text = 10.toString(),
                                    style = AppTheme.TextStyles.body
                                )
                            },
                            onClick = { obtainViewAction(MainViewAction.SetItemsToShow(10)) },
                        )
                        DropdownMenuItem(
                            text = {
                                BasicText(
                                    text = 15.toString(),
                                    style = AppTheme.TextStyles.body
                                )
                            },
                            onClick = { obtainViewAction(MainViewAction.SetItemsToShow(15)) },
                        )
                        DropdownMenuItem(
                            text = {
                                BasicText(
                                    text = 20.toString(),
                                    style = AppTheme.TextStyles.body
                                )
                            },
                            onClick = { obtainViewAction(MainViewAction.SetItemsToShow(20)) },
                        )
                    }
                }

            }
            when (tabIndex) {
                0 -> TransactionsList(
                    transactions = state.transactions,
                    itemsToShow = state.itemsToShow
                )

                1 -> TransactionsList(
                    transactions = state.transactions.filter { transaction: Transaction ->
                        transaction.type == TransactionType.EXPENSE
                    },
                    itemsToShow = state.itemsToShow
                )
            }
        }
    }
}

@Composable
private fun CategoryExpensesCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.padding(all = AppTheme.Dimens.dimen20)) {
            BasicText(
                text = stringResource(Res.string.main_screen_expenses_by_categories_card_title),
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )
        }
    }
}

@Composable
private fun BalanceCard(
    modifier: Modifier = Modifier,
    state: MainState,
    obtainViewAction: (MainViewAction) -> Unit,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppTheme.Dimens.dimen20)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(all = AppTheme.Dimens.dimen20),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BalanceCardTitle(
                    title = stringResource(Res.string.main_screen_balance_card_current_balance_title),
                    sum = 500.toString()
                )

                BalanceCardTitle(
                    title = stringResource(Res.string.main_screen_balance_card_current_expenses_title),
                    sum = 700.toString(),
                    color = AppTheme.Colors.red
                )

                BalanceCardTitle(
                    title = stringResource(Res.string.main_screen_balance_card_current_income_title),
                    sum = 800.toString(),
                    color = AppTheme.Colors.green
                )
            }

            BasicText(
                modifier = Modifier.padding(horizontal = AppTheme.Dimens.dimen20),
                text = stringResource(Res.string.main_screen_balance_card_accounts_title),
                style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

            LazyRow(modifier = Modifier.fillMaxSize()) {
                items(40) {
                    Card(
                        modifier = Modifier.fillMaxWidth().size(100.dp)
                            .background(color = AppTheme.Colors.red)
                    ) {
                        Button(
                            onClick = {},
                            content = {
                                BasicText(text = "Нажми")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AccountItem(
    modifier: Modifier = Modifier,
    account: Account,
    obtainViewAction: (MainViewAction) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(AppTheme.Dimens.dimen8)
            .width(AppTheme.Dimens.dimen100)
            .clickable { obtainViewAction(MainViewAction.AccountClick(account.id)) }
    ) {
        BasicText(
            text = account.name,
            style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

        BasicText(
            text = account.balance.toString() + " ${account.currency}",
            style = AppTheme.TextStyles.subtitle.copy(AppTheme.Colors.black)
        )
    }
}

@Composable
private fun BalanceCardTitle(
    modifier: Modifier = Modifier,
    title: String,
    sum: String,
    color: Color = AppTheme.Colors.black,
) {
    Column(modifier = modifier) {
        BasicText(
            text = title,
            style = AppTheme.TextStyles.title.copy(AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen2))

        BasicText(
            text = sum,
            style = AppTheme.TextStyles.headlineTitle.copy(color = color)
        )
    }
}

@Composable
private fun CategoriesPie(modifier: Modifier = Modifier) {

}

@Composable
private fun TransactionsList(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    itemsToShow: Int,
) {
    LazyColumn(modifier = modifier) {
        items(transactions.take(itemsToShow)) {
            TransactionItem(
                icon = AppIcons.PlusIcon.painter,
                title = it.name,
                amount = it.amount,
                type = it.type,
                account = it.account.name,
                currency = it.account.currency,
                category = it.category.name
            )
        }
    }
}

@Composable
private fun TransactionDialog(
    onDismissRequest: () -> Unit,
    state: MainState,
    obtainViewAction: (MainViewAction) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.Dimens.dimen16).sizeIn(280.dp, 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppTextField(
                value = state.newTransactionTitle,
                onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                hint = stringResource(Res.string.main_screen_title)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            AppTextField(
                value = state.newTransactionType?.name ?: "",
                onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                hint = stringResource(Res.string.main_screen_title)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            Row(modifier = Modifier.fillMaxWidth()) {
                AppTextField(
                    value = state.newTransactionSumma.toString(),
                    onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                    hint = stringResource(Res.string.main_screen_title)
                )


                //DropDown
            }

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            AppTextField(
                value = state.newTransactionType?.name ?: "",
                onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                hint = stringResource(Res.string.main_screen_title)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            AppTextField(
                value = state.newTransactionTitle,
                onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                hint = stringResource(Res.string.main_screen_title)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            AppTextField(
                value = state.newTransactionTitle,
                onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                hint = stringResource(Res.string.main_screen_title)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            AppTextField(
                value = state.newTransactionTitle,
                onValueChange = { obtainViewAction(MainViewAction.UpdateNewTransactionTitle(it)) },
                hint = stringResource(Res.string.main_screen_title)
            )
        }
    }
}

@Composable
@Preview
private fun AddButtonPreview() {
    AddButton(
        text = "text",
        onClick = {},
        shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
        painter = AppIcons.PlusIcon.painter,
        contentDescription = AppIcons.PlusIcon.contentDescription
    )
}
