package ru.crazerr.cashtracker.feature.transactions.presentation.transactions.handler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.SummaryInfo
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary.GetSummaryInfoResult
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponent

internal class GetSummaryInfoResultHandler(
    private val result: GetSummaryInfoResult,
    private val delegate: TransactionsComponent,
    private val coroutineScope: CoroutineScope,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetSummaryInfoResult.Ok -> onOk(summaryInfo = result.summaryInfo)
            GetSummaryInfoResult.NetworkError -> onNetworkError()
            is GetSummaryInfoResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(summaryInfo: Flow<SummaryInfo>) {
        coroutineScope.launch {
            summaryInfo.collectLatest {
                delegate.reduceState {
                    copy(
                        summaryExpenses = it.expenses,
                        summaryIncome = it.income,
                        mostExpensiveCategories = it.mostExpensiveCategories
                    )
                }
            }
        }
    }
}
