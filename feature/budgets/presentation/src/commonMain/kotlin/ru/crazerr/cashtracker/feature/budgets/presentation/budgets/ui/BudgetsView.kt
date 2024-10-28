package ru.crazerr.cashtracker.feature.budgets.presentation.budgets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.crazerr.cashtracker.core.compose.components.AddButton
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.feature.budget.domain.api.model.BudgetCategory
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponent
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsState
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsViewAction

@Composable
internal fun BudgetsView(modifier: Modifier = Modifier, component: BudgetsComponent) {
    val state by component.state.subscribeAsState()

    BudgetsContentView(
        modifier = modifier,
        state = state,
        obtainViewAction = component::obtainViewAction
    )
}

@Composable
private fun BudgetsContentView(
    modifier: Modifier = Modifier,
    state: BudgetsState,
    obtainViewAction: (BudgetsViewAction) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        BasicText(text = "Бюджет", style = AppTheme.TextStyles.headlineTitle)

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        Row(modifier = Modifier.weight(2f)) {
            BudgetList(
                modifier = Modifier.weight(2f),
                budgets = state.budgets,
                obtainViewAction = obtainViewAction
            )

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen12))

            BudgetCircleChart(
                modifier = Modifier.weight(1f),
                state = state,
                obtainViewAction = obtainViewAction
            )
        }

        LineChart(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun LineChart(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            BasicText("Расходы с течением времени", style = AppTheme.TextStyles.subtitle)

            Spacer(modifier = Modifier.weight(1f))

            BasicText(text = "Начальная дата")

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

            BasicText(text = "Конечная дата")
        }

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))
    }
}

@Composable
private fun BudgetCategoryItem(
    modifier: Modifier = Modifier,
    budgetCategory: BudgetCategory,
    obtainViewAction: (BudgetsViewAction) -> Unit
) {
    Card(
        modifier = modifier
            .padding(AppTheme.Dimens.dimen12)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicText(
                    text = budgetCategory.category.name,
                    style = AppTheme.TextStyles.subtitle
                )

                Spacer(modifier = Modifier.weight(1f))

                AppIconButton(
                    icon = AppIcons.PlusIcon.painter,
                    contentDescription = AppIcons.PlusIcon.contentDescription,
                    onClick = { obtainViewAction(BudgetsViewAction.EditBudgetClick(budgetCategory.id)) }
                )

                Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))

                AppIconButton(
                    icon = AppIcons.PlusIcon.painter,
                    contentDescription = AppIcons.PlusIcon.contentDescription,
                    onClick = { obtainViewAction(BudgetsViewAction.DeleteBudgetClick(budgetCategory.id)) }
                )
            }

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                BasicText(text = "Прогресс:", style = AppTheme.TextStyles.body)

                BasicText(
                    text = (budgetCategory.maxAmount / budgetCategory.currentAmount * 100).toString(),
                    style = AppTheme.TextStyles.body
                )
            }

            LinearProgressIndicator(progress = {
                if (budgetCategory.maxAmount / budgetCategory.currentAmount > 1.0) {
                    1f
                } else {
                    budgetCategory.maxAmount / budgetCategory.currentAmount
                }
            })

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

            BudgetCategoryItemFooter(budgetCategory = budgetCategory)
        }
    }
}

@Composable
private fun BudgetCategoryItemFooter(
    modifier: Modifier = Modifier,
    budgetCategory: BudgetCategory
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BasicText(
            text = "Осталось: ${budgetCategory.maxAmount - budgetCategory.currentAmount}",
            style = AppTheme.TextStyles.caption
        )

        BasicText(text = "Дата последней транзакции: ${budgetCategory.lastTransactionDate}")
    }
}

@Composable
private fun BudgetList(
    modifier: Modifier = Modifier,
    obtainViewAction: (BudgetsViewAction) -> Unit,
    budgets: List<BudgetCategory>
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(text = "Категории", style = AppTheme.TextStyles.subtitle)

            AddButton(
                text = "Добавить бюджет",
                onClick = { obtainViewAction(BudgetsViewAction.AddNewBudgetClick) }
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(budgets) {
                BudgetCategoryItem(budgetCategory = it, obtainViewAction = obtainViewAction)
            }
        }
    }
}

@Composable
private fun BudgetCircleChart(
    modifier: Modifier = Modifier,
    state: BudgetsState,
    obtainViewAction: (BudgetsViewAction) -> Unit
) {
    Column(modifier = modifier) {
        BasicText(
            text = "Процент категории от общего бюджета",
            style = AppTheme.TextStyles.headlineTitle
        )

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))
    }
}
