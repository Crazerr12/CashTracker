package ru.crazerr.cashtracker.feature.transactions.presentation.transactions.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import cashtracker.feature.transactions.presentation.generated.resources.Res
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_button_add_new_transaction
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_search_bar_hint
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_subtitle_diagram
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_subtitle_top_categories
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_text_expenses
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_text_income
import cashtracker.feature.transactions.presentation.generated.resources.transactions_screen_summary_card_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ru.crazerr.cashtracker.core.compose.components.AddButton
import ru.crazerr.cashtracker.core.compose.components.AppButton
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.icons.getIconById
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.dateTime.getMonthNames
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.core.utils.dateTime.toEpochMilliSeconds
import ru.crazerr.cashtracker.core.utils.dateTime.toLocalDate
import ru.crazerr.cashtracker.core.utils.formatter.CurrencyFormatter
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewFactory
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.CategoryShare
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponent
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsState
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsViewAction
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

private const val MAX_FILTER_TEXT_LINES = 1

@Composable
actual fun TransactionsView(component: TransactionsComponent) {
    val state by component.state.subscribeAsState()
    val dialogState by component.dialog.subscribeAsState()

    dialogState.child?.instance?.also {
        when (it) {
            is TransactionsComponent.DialogChild.CreateTransactionDialog -> {
                val createAccountViewFactory = koinInject<CreateTransactionViewFactory>()
                createAccountViewFactory.create(modifier = Modifier, component = it.component)
            }
        }
    }

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
                text = "Транзакции",
                style = AppTheme.TextStyles.headlineTitle.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen16))

            TabsWithSearchRow(state = state, obtainViewAction = obtainViewAction)

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

            FilterRow(state = state, obtainViewAction = obtainViewAction)

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
        }

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        SummaryCard(
            state = state,
            obtainViewAction = obtainViewAction
        )
    }
}

@Composable
private fun FilterRow(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        FilterItem(
            text = "Дата",
            onClick = { obtainViewAction(TransactionsViewAction.ManageDateFilterDropdown) },
            isExpand = state.dateFilterIsExpanded,
            content = {
                DateFilterDropdown(
                    modifier = Modifier.size(400.dp),
                    state = state,
                    obtainViewAction = obtainViewAction
                )
            }
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        FilterItem(
            text = "Категории",
            onClick = { obtainViewAction(TransactionsViewAction.ManageCategoriesFilterDropdown) },
            isExpand = state.categoriesFilterIsExpanded,
            content = {
                ListDropdownContent(
                    isAllItemsChecked = state.allCategoriesChecked,
                    checkAllItemsText = "Все категории",
                    onCheck = { obtainViewAction(TransactionsViewAction.CategoryCheckboxClick(it)) },
                    onAllItemsClick = {
                        obtainViewAction(
                            TransactionsViewAction.AllCategoriesCheckboxClick(
                                it
                            )
                        )
                    },
                    items = state.categoriesFilter
                )
            }
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        FilterItem(
            text = "Аккаунты",
            onClick = { obtainViewAction(TransactionsViewAction.ManageAccountsFilterDropdown) },
            isExpand = state.accountsFilterIsExpanded,
            content = {
                ListDropdownContent(
                    isAllItemsChecked = state.allAccountsChecked,
                    checkAllItemsText = "Все аккаунты",
                    onCheck = { obtainViewAction(TransactionsViewAction.AccountCheckboxClick(it)) },
                    onAllItemsClick = {
                        obtainViewAction(
                            TransactionsViewAction.AllAccountsCheckboxClick(
                                it
                            )
                        )
                    },
                    items = state.accountsFilter
                )
            }
        )
    }
}

@Composable
private fun FilterItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isExpand: Boolean,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.Dimens.dimen8))
                .border(
                    border = BorderStroke(width = 1.dp, color = AppTheme.Colors.black),
                    shape = RoundedCornerShape(AppTheme.Dimens.dimen8)
                )
                .clickable { onClick() }
                .padding(AppTheme.Dimens.dimen4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                text = text,
                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

            Icon(
                painter = if (isExpand) AppIcons.AngleUp.painter else AppIcons.AngleDown.painter,
                contentDescription = if (isExpand) {
                    AppIcons.AngleUp.contentDescription
                } else {
                    AppIcons.AngleDown.contentDescription
                }
            )
        }
        DropdownMenu(
            expanded = isExpand,
            onDismissRequest = onClick
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateFilterDropdown(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = state.startDateFilter.toEpochMilliSeconds(),
        initialSelectedEndDateMillis = state.endDateFilter.toEpochMilliSeconds(),
        initialDisplayMode = DisplayMode.Picker,
        yearRange = 2010..LocalDate.now().year
    )

    LaunchedEffect(
        dateRangePickerState.selectedStartDateMillis,
        dateRangePickerState.selectedEndDateMillis
    ) {
        if (dateRangePickerState.selectedStartDateMillis != null) {
            obtainViewAction(
                TransactionsViewAction.SetStartDateFilter(
                    date = dateRangePickerState.selectedStartDateMillis!!.toLocalDate()
                )
            )
        }
        if (dateRangePickerState.selectedEndDateMillis != null) {
            obtainViewAction(
                TransactionsViewAction.SetEndDateFilter(
                    date = dateRangePickerState.selectedEndDateMillis!!.toLocalDate()
                )
            )
        }
    }

    DateRangePicker(
        title = null,
        headline = null,
        showModeToggle = false,
        modifier = modifier,
        state = dateRangePickerState,
    )
}

@Composable
private fun <T> ListDropdownContent(
    isAllItemsChecked: Boolean,
    checkAllItemsText: String,
    onCheck: (T) -> Unit,
    onAllItemsClick: (Boolean) -> Unit,
    items: Map<T, Boolean>,
) {
    DropdownMenuItem(
        onClick = { onAllItemsClick(isAllItemsChecked) }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isAllItemsChecked,
                onCheckedChange = { onAllItemsClick(isAllItemsChecked) },
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

            BasicText(
                text = checkAllItemsText,
                style = AppTheme.TextStyles.body
            )
        }
    }

    items.entries.forEach { item ->
        DropdownMenuItem(onClick = { onCheck(item.key) }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = item.value,
                    onCheckedChange = { onCheck(item.key) },
                )

                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

                BasicText(
                    text = when (val key = item.key) {
                        is Account -> key.name
                        is Category -> key.name
                        else -> ""
                    },
                    maxLines = MAX_FILTER_TEXT_LINES,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.TextStyles.body
                )
            }
        }
    }
}

@Composable
private fun TabsWithSearchRow(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
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

        Spacer(modifier = Modifier.weight(0.2f))

        Box(modifier = Modifier.weight(1f)) {
            AppTextField(
                value = state.query,
                onValueChange = { obtainViewAction(TransactionsViewAction.SearchChange(it)) },
                hint = stringResource(Res.string.transactions_screen_search_bar_hint),
            )
        }
    }
}

@Composable
private fun TransactionsList(
    modifier: Modifier = Modifier,
    state: TransactionsState,
    obtainViewAction: (TransactionsViewAction) -> Unit,
) {
    val transactions = state.transactions.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = AppTheme.Dimens.dimen8),
        verticalArrangement = Arrangement.spacedBy(AppTheme.Dimens.dimen8)
    ) {
        items(
            count = transactions.itemCount,
            key = transactions.itemKey { it.id }
        ) { index ->
            val item = transactions[index]

            if (item != null) {
                TransactionItem(transaction = item)
            }
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
    val transactionSum = transactions.fold(0.0) { sum, transaction ->
        if (transaction.type == TransactionType.EXPENSE) {
            sum + transaction.amount
        } else {
            sum - transaction.amount
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
            Column(modifier = Modifier.fillMaxWidth()) {
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
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = getIconById(transaction.category.iconId).painter,
            contentDescription = getIconById(transaction.category.iconId).contentDescription,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

        Column(modifier = Modifier) {
            BasicText(
                text = transaction.name,
                style = AppTheme.TextStyles.body.copy(color = AppTheme.Colors.black)
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen2))

            Row(modifier = Modifier) {
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
            text = "${CurrencyFormatter().format(transaction.amount)}${transaction.account.currency.symbolNative}",
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
            .padding(AppTheme.Dimens.dimen10)
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
private fun MostExpensiveCategories(categories: List<CategoryShare>) {
    BasicText(
        text = stringResource(Res.string.transactions_screen_summary_card_subtitle_top_categories)
    )

    Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

    categories.forEachIndexed { index, category ->
        StatItem(
            title = category.name,
            value = category.sum.toString(),
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
