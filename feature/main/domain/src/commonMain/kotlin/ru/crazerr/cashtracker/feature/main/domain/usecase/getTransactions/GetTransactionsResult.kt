package ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions

import ru.crazerr.cashtracker.feature.main.domain.model.Transaction

sealed interface GetTransactionsResult {
    data class Ok(val transactions: List<Transaction>) : GetTransactionsResult

    data object NetworkError : GetTransactionsResult

    data class UnknownError(val throwable: Throwable?) : GetTransactionsResult
}
