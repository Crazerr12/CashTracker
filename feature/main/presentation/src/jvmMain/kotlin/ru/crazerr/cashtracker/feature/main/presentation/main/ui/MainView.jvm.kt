package ru.crazerr.cashtracker.feature.main.presentation.main.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cashtracker.feature.main.presentation.generated.resources.Res
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_accounts_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_current_balance_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_current_expenses_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_balance_card_current_income_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_import_data
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_account
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_budget
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_goal
import cashtracker.feature.main.presentation.generated.resources.main_screen_button_text_new_transaction
import cashtracker.feature.main.presentation.generated.resources.main_screen_expenses_by_categories_card_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_percent_example
import cashtracker.feature.main.presentation.generated.resources.main_screen_ruble_example
import cashtracker.feature.main.presentation.generated.resources.main_screen_title
import cashtracker.feature.main.presentation.generated.resources.main_screen_transactions_card_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ru.crazerr.cashtracker.core.compose.components.AddButton
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.dateTime.getMonthNames
import ru.crazerr.cashtracker.core.utils.dateTime.toInput
import ru.crazerr.cashtracker.core.utils.formatter.CurrencyFormatter
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewFactory
import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent
import ru.crazerr.cashtracker.feature.main.presentation.main.MainState
import ru.crazerr.cashtracker.feature.main.presentation.main.MainViewAction
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewFactory
import kotlin.math.min

private const val BALANCE_CARD_TITLE_MAX_LINE = 1
private const val TEN_TRANSACTIONS_TO_SHOW = 10
private const val FIFTEEN_TRANSACTIONS_TO_SHOW = 15
private const val TWENTY_TRANSACTIONS_TO_SHOW = 20

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

            MainComponent.DialogChild.CreateBudget -> {
                // TODO
            }

            MainComponent.DialogChild.CreateGoal -> {
                // TODO
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

                CategorySharesCard(modifier = Modifier.fillMaxHeight().weight(1f), state = state)
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

@Suppress("LongMethod")
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
            onClick = { obtainViewAction(MainViewAction.ManageAccountDialog) },
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
            onClick = { obtainViewAction(MainViewAction.PreviousMonthButtonClick) },
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
            onClick = { obtainViewAction(MainViewAction.NextMonthButtonClick) },
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
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(all = AppTheme.Dimens.dimen20)) {
            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicText(
                    text = stringResource(resource = Res.string.main_screen_transactions_card_title),
                    style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
                )

                Spacer(modifier = Modifier.weight(1f).widthIn(min = AppTheme.Dimens.dimen10))

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen8))
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = AppTheme.Colors.black
                            ),
                            shape = RoundedCornerShape(AppTheme.Dimens.dimen8)
                        )
                        .clickable {
                            obtainViewAction(MainViewAction.ManageTransactionsToShowDropdownMenu)
                        }
                        .padding(AppTheme.Dimens.dimen4),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicText(
                            text = state.transactionsToShow.toString(),
                            style = AppTheme.TextStyles.body.copy()
                        )

                        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

                        Icon(
                            painter = if (state.itemsToShowIsExpanded) {
                                AppIcons.AngleUp.painter
                            } else {
                                AppIcons.AngleDown.painter
                            },
                            contentDescription = if (state.itemsToShowIsExpanded) {
                                AppIcons.AngleUp.contentDescription
                            } else {
                                AppIcons.AngleDown.contentDescription
                            },
                        )
                    }

                    TransactionsToShowDropdown(state = state, obtainViewAction = obtainViewAction)
                }
            }

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen20))

            TransactionsList(transactions = state.transactions)
        }
    }
}

@Composable
private fun TransactionsToShowDropdown(
    modifier: Modifier = Modifier,
    state: MainState,
    obtainViewAction: (MainViewAction) -> Unit,
) {
    DropdownMenu(
        modifier = modifier,
        expanded = state.itemsToShowIsExpanded,
        onDismissRequest = { obtainViewAction(MainViewAction.ManageTransactionsToShowDropdownMenu) },
    ) {
        DropdownMenuItem(
            text = {
                BasicText(
                    text = TEN_TRANSACTIONS_TO_SHOW.toString(),
                    style = AppTheme.TextStyles.body
                )
            },
            onClick = {
                obtainViewAction(
                    MainViewAction.SetTransactionsToShow(
                        TEN_TRANSACTIONS_TO_SHOW
                    )
                )
            },
        )
        DropdownMenuItem(
            text = {
                BasicText(
                    text = FIFTEEN_TRANSACTIONS_TO_SHOW.toString(),
                    style = AppTheme.TextStyles.body
                )
            },
            onClick = {
                obtainViewAction(
                    MainViewAction.SetTransactionsToShow(
                        FIFTEEN_TRANSACTIONS_TO_SHOW
                    )
                )
            },
        )
        DropdownMenuItem(
            text = {
                BasicText(
                    text = TWENTY_TRANSACTIONS_TO_SHOW.toString(),
                    style = AppTheme.TextStyles.body
                )
            },
            onClick = {
                obtainViewAction(
                    MainViewAction.SetTransactionsToShow(
                        TWENTY_TRANSACTIONS_TO_SHOW
                    )
                )
            },
        )
    }
}

@Composable
private fun CategorySharesCard(modifier: Modifier = Modifier, state: MainState) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize().padding(all = AppTheme.Dimens.dimen20)) {
            BasicText(
                text = stringResource(Res.string.main_screen_expenses_by_categories_card_title),
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            Row(modifier = Modifier.fillMaxWidth()) {
                CategorySharesChart(modifier = Modifier.weight(1f), state.categoryShares)

                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = AppTheme.Dimens.dimen8)
                ) {
                    items(state.categoryShares) {
                        CategorySharesItem(categoryShare = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun CategorySharesItem(modifier: Modifier = Modifier, categoryShare: CategoryShare) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(AppTheme.Dimens.dimen14)
                .clip(shape = CircleShape)
                .background(color = Color(categoryShare.color), shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

        BasicText(
            text = categoryShare.name,
            style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        BasicText(
            text = stringResource(
                Res.string.main_screen_percent_example,
                CurrencyFormatter().format(categoryShare.percent)
            ),
            style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
        )
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
            BalanceCardTitleRow(state = state)

            BasicText(
                modifier = Modifier.padding(horizontal = AppTheme.Dimens.dimen20),
                text = stringResource(Res.string.main_screen_balance_card_accounts_title),
                style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = AppTheme.Dimens.dimen20,
                    end = AppTheme.Dimens.dimen20,
                ),
            ) {
                items(state.accounts) {
                    AccountItem(account = it, obtainViewAction = obtainViewAction)
                }
            }
        }
    }
}

@Composable
private fun BalanceCardTitleRow(modifier: Modifier = Modifier, state: MainState) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(all = AppTheme.Dimens.dimen20),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BalanceCardTitle(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.main_screen_balance_card_current_balance_title),
            sum = stringResource(
                Res.string.main_screen_ruble_example,
                state.currentBalance
            )
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        BalanceCardTitle(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.main_screen_balance_card_current_expenses_title),
            sum = stringResource(
                Res.string.main_screen_ruble_example,
                state.expensesByMonth
            ),
            color = AppTheme.Colors.red
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        BalanceCardTitle(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.main_screen_balance_card_current_income_title),
            sum = stringResource(
                Res.string.main_screen_ruble_example,
                state.incomeByMonth
            ),
            color = AppTheme.Colors.green
        )
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
            .fillMaxSize()
            .background(
                color = AppTheme.Colors.gray,
                shape = RoundedCornerShape(AppTheme.Dimens.dimen8)
            )
            .clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen8))
            .clickable { obtainViewAction(MainViewAction.AccountClick(account.id)) }
            .padding(all = AppTheme.Dimens.dimen10),
        verticalArrangement = Arrangement.Center
    ) {
        BasicText(
            modifier = Modifier,
            text = account.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

        BasicText(
            modifier = Modifier,
            text = "${account.balance} ${account.currency.symbolNative}",
            style = AppTheme.TextStyles.subtitle.copy(
                color = if (account.balance >= 0) AppTheme.Colors.green else AppTheme.Colors.red
            )
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
            maxLines = BALANCE_CARD_TITLE_MAX_LINE,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.TextStyles.title.copy(AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen2))

        BasicText(
            maxLines = BALANCE_CARD_TITLE_MAX_LINE,
            text = sum,
            style = AppTheme.TextStyles.headlineTitle.copy(color = color)
        )
    }
}

@Composable
private fun CategorySharesChart(
    modifier: Modifier = Modifier,
    categoryShares: List<CategoryShare>,
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            var startAngle = 0f

            categoryShares.forEach {
                val sweepAngle = it.percent.mapValueToDifferentRange(
                    inMin = 0f,
                    inMax = 100f,
                    outMin = 0f,
                    outMax = 360f
                )

                drawArc(
                    color = Color(it.color),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle
                )

                startAngle += sweepAngle
            }
        }
    }
}

private fun DrawScope.drawArc(
    color: Color,
    startAngle: Float,
    sweepAngle: Float,
) {
    val padding = 48.dp.toPx() // some padding to avoid arc touching the border
    val sizeMin = min(size.width, size.height)
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false, // draw arc without infill
        size = Size(sizeMin - padding, sizeMin - padding), // size of the arc/circle in pixels
        style = Stroke( // width of the ark
            width = sizeMin / 10
        ),
        topLeft = Offset(padding / 2f, padding / 2f) // move the arc to center
    )
}

fun Float.mapValueToDifferentRange(
    inMin: Float,
    inMax: Float,
    outMin: Float,
    outMax: Float,
) = (this - inMax) * (outMax - outMin) / (inMax - inMin) + outMin

@Composable
private fun TransactionsList(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
) {
    LazyColumn(modifier = modifier) {
        items(transactions) {
            TransactionView(transaction = it)
        }
    }
}

@Composable
private fun TransactionView(modifier: Modifier = Modifier, transaction: Transaction) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = AppIcons.Transactions.painter,
            contentDescription = AppIcons.Transactions.contentDescription
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        Column(modifier = Modifier) {
            BasicText(
                text = transaction.name,
                style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black),
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

            Row(modifier = Modifier, horizontalArrangement = Arrangement.End) {
                BasicText(
                    text = transaction.category.name,
                    style = AppTheme.TextStyles.body.copy(
                        color = AppTheme.Colors.gray,
                        textAlign = TextAlign.End
                    ),
                )

                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

                BasicText(
                    text = transaction.account.name,
                    style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.gray)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        Column(modifier = Modifier) {
            BasicText(
                text = stringResource(Res.string.main_screen_ruble_example, transaction.amount),
                style = AppTheme.TextStyles.subtitle.copy(
                    color = if (transaction.type == TransactionType.INCOME) {
                        AppTheme.Colors.green
                    } else {
                        AppTheme.Colors.red
                    }
                )
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

            BasicText(
                text = transaction.date.toInput(),
                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.gray)
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
