package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions

import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

sealed interface GetTransactionsResult {
    data class Ok(val transactions: List<Transaction>) : GetTransactionsResult
    data object NetworkError : GetTransactionsResult
    data class UnknownError(val throwable: Throwable) : GetTransactionsResult
}
