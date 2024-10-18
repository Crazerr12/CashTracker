package ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth

import ru.crazerr.cashtracker.feature.main.domain.model.ExpensesAndIncome

sealed interface GetExpensesAndIncomeByMonthResult {
    data class Ok(val expensesAndIncome: ExpensesAndIncome) : GetExpensesAndIncomeByMonthResult
    data object NetworkError : GetExpensesAndIncomeByMonthResult
    data class UnknownError(val throwable: Throwable) : GetExpensesAndIncomeByMonthResult
}
