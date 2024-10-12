package ru.crazerr.cashtracker.feature.main.presentation.main.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.main.domain.model.Transaction
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsResult
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent

class GetTransactionsResultHandler(
    private val result: GetTransactionsResult,
    private val delegate: MainComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetTransactionsResult.Ok -> onOk(result.transactions)
            GetTransactionsResult.NetworkError -> onNetworkError()
            is GetTransactionsResult.UnknownError -> onUnknownError(result.throwable)
        }
    }

    private fun onOk(transactions: List<Transaction>) {
        delegate.reduceState {
            copy(
                transactions = transactions
            )
        }
    }
}
