package ru.crazerr.cashtracker.feature.transactions.presentation.transactions.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cashtracker.feature.transactions.presentation.generated.resources.Res
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_button_add_new_transaction
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_search_bar_hint
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_subtitle_diagram
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_text_expenses
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_text_income
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource
import ru.crazerr.cashtracker.core.compose.components.AddButton
import ru.crazerr.cashtracker.core.compose.components.AppButton
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.dateTime.getMonthNames
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponent
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsState
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsViewAction
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

@Composable
actual fun TransactionsView(component: TransactionsComponent) {
    val state by component.state.subscribeAsState()

    TransactionsViewContent(
        state = state,
        obtainViewAction = component::obtainViewAction
    )
}

@Composable
private fun TransactionsViewContent(
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize()
            .padding(AppTheme.Dimens.dimen20)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight()
        ) {
            BasicText(
                text = "Transactions",
                style = AppTheme.TextStyles.headlineTitle.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen16))

            TabsWithSearchRow(state = state, obtainViewAction = obtainViewAction)

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            FilterRow()

            TransactionsList(
                modifier = Modifier.weight(1f),
                state = state,
                obtainViewAction = obtainViewAction
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

            AddButton(
                text = stringResource(Res.string.transactions_screen_button_add_new_transaction),
                onClick = { obtainViewAction(TransactionsViewAction.CreateNewTransactionClick) }
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        SummaryCard(
            state = state,
            obtainViewAction = obtainViewAction
        )
    }
}

@Composable
private fun FilterRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        FilterItem { }
        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))
        FilterItem { }
        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))
        FilterItem { }
    }
}

@Composable
private fun FilterItem(onClick: () -> Unit) {
    Box {
        Row(modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(AppTheme.Dimens.dimen16))
            .border(
                border = BorderStroke(width = 1.dp, color = AppTheme.Colors.black),
                shape = RoundedCornerShape(AppTheme.Dimens.dimen16)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}

@Composable
private fun TabsWithSearchRow(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        TransactionsTab.entries.forEach { tab ->
            AppButton(
                text = tab.name,
                onClick = { obtainViewAction(TransactionsViewAction.SelectTab(tab)) },
                backgroundColor = if (state.selectedTab == tab) AppTheme.Colors.blue else AppTheme.Colors.background,
                contentColor = if (state.selectedTab == tab) AppTheme.Colors.white else AppTheme.Colors.black
            )

            if (tab != TransactionsTab.Income) {
                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen10))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AppTextField(
            value = state.searchQuery,
            onValueChange = {},
            hint = stringResource(Res.string.transactions_screen_search_bar_hint),
        )
    }
}

@Composable
private fun TransactionsList(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(state.transactions.entries.toList()) { entry ->
            TransactionCard(
                header = entry.key,
                transactions = entry.value,
                obtainViewAction = obtainViewAction,
            )
        }
    }
}

@Composable
private fun TransactionCard(
    modifier: Modifier = Modifier,
    header: TransactionHeader,
    transactions: List<Transaction>,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    var transactionSum = 0f
    transactions.forEach { transaction ->
        if (transaction.type == TransactionType.EXPENSE) {
            transactionSum += transaction.amount
        } else {
            transactionSum -= transaction.amount
        }
    }

    Card(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = AppIcons.PlusIcon.painter,
                contentDescription = AppIcons.PlusIcon.contentDescription,
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen10))

            BasicText(
                text = LocalDate.Format {
                    dayOfMonth()
                    char(' ')
                    monthName(MonthNames(getMonthNames("ru")))
                    char(' ')
                    year()
                }.format(header.date),
                style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))
            Spacer(modifier = Modifier.weight(1f))

            BasicText(
                text = transactions.count().toString(),
                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen10))

            BasicText(
                text = transactionSum.toString(),
                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen10))

            AppIconButton(
                icon = if (header.isExpand) AppIcons.AngleUp.painter else AppIcons.AngleDown.painter,
                onClick = { obtainViewAction(TransactionsViewAction.ManageTransactionHeader(header = header)) }
            )
        }

        if (header.isExpand) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Divider(modifier = Modifier.fillMaxWidth())

                transactions.forEach { transaction ->
                    TransactionItem(transaction = transaction)
                }
            }
        }
    }
}

@Composable
private fun TransactionItem(modifier: Modifier = Modifier, transaction: Transaction) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.fillMaxWidth()) {
            BasicText(
                text = transaction.name,
                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                BasicText(
                    text = transaction.category.name,
                    style = AppTheme.TextStyles.caption.copy(color = AppTheme.Colors.gray)
                )

                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

                BasicText(
                    text = transaction.account.name,
                    style = AppTheme.TextStyles.caption.copy(color = AppTheme.Colors.gray)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        BasicText(
            text = transaction.amount.toString(),
            style = AppTheme.TextStyles.subtitle.copy(
                color = if (transaction.type == TransactionType.EXPENSE) AppTheme.Colors.red else AppTheme.Colors.green
            )
        )
    }
}

@Composable
private fun SummaryCard(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen16))
            .background(
                color = AppTheme.Colors.background,
                shape = RoundedCornerShape(AppTheme.Dimens.dimen16)
            )
    ) {
        BasicText(
            text = stringResource(Res.string.transactions_screen_summary_card_title),
            style = AppTheme.TextStyles.title.copy(AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        IncomeExpensesSummary(income = state.summaryIncome, expenses = state.summaryExpenses)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

        MostExpensiveCategories(
            categories = state.mostExpensiveCategories
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

        SpendingDiagram()

        YandexAd(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun IncomeExpensesSummary(income: Float, expenses: Float) {
    StatItem(
        title = stringResource(Res.string.transactions_screen_summary_card_text_income),
        value = income.toString(),
        color = AppTheme.Colors.green
    )

    Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

    StatItem(
        title = stringResource(Res.string.transactions_screen_summary_card_text_expenses),
        value = expenses.toString(),
        color = AppTheme.Colors.red
    )
}

@Composable
private fun MostExpensiveCategories(categories: Map<String, Int>) {
    BasicText(
        text = stringResource(Res.string.transactions_screen_summary_card_text_expenses)
    )

    Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

    categories.entries.forEachIndexed { index, category ->
        StatItem(
            title = category.key,
            value = category.value.toString(),
        )

        if (index != 2) {
            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))
        }
    }
}

@Composable
private fun SpendingDiagram() {
    BasicText(
        text = stringResource(Res.string.transactions_screen_summary_card_subtitle_diagram),
        style = AppTheme.TextStyles.subtitle.copy(color = AppTheme.Colors.black)
    )

    Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))
}

@Composable
private fun YandexAd(modifier: Modifier = Modifier) {

}

@Composable
private fun StatItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    color: Color = AppTheme.Colors.black,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        BasicText(
            text = title,
            style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))
        Spacer(modifier = Modifier.weight(1f))

        BasicText(
            text = value,
            style = AppTheme.TextStyles.body.copy(color = color)
        )
    }
}