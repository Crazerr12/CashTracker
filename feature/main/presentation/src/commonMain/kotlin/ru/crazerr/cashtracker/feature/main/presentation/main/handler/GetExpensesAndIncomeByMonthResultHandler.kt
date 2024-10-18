package ru.crazerr.cashtracker.feature.main.presentation.main.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.main.domain.model.ExpensesAndIncome
import ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth.GetExpensesAndIncomeByMonthResult
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent

internal class GetExpensesAndIncomeByMonthResultHandler(
    private val result: GetExpensesAndIncomeByMonthResult,
    private val delegate: MainComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetExpensesAndIncomeByMonthResult.Ok -> onOk(
                expensesAndIncome =
                result.expensesAndIncome
            )

            GetExpensesAndIncomeByMonthResult.NetworkError -> onNetworkError()
            is GetExpensesAndIncomeByMonthResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(expensesAndIncome: ExpensesAndIncome) {
        delegate.reduceState {
            copy(
                expensesByMonth = expensesAndIncome.totalExpenses,
                incomeByMonth = expensesAndIncome.totalIncome
            )
        }
    }
}
