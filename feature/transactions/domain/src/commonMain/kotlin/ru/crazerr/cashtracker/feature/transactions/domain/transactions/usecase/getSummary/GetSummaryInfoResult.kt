package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.SummaryInfo

sealed interface GetSummaryInfoResult {
    data class Ok(val summaryInfo: Flow<SummaryInfo>) : GetSummaryInfoResult
    data object NetworkError : GetSummaryInfoResult
    data class UnknownError(val throwable: Throwable) : GetSummaryInfoResult
}
