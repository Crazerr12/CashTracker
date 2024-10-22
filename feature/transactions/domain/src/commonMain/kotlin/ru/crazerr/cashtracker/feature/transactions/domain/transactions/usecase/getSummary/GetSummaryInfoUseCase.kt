package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

interface GetSummaryInfoUseCase : UseCase<GetSummaryInfoUseCase.Param, GetSummaryInfoResult> {
    data class Param(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val query: String,
        val accountIds: List<Long>,
        val categoryIds: List<Long>,
    )
}

internal class GetSummaryInfoUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetSummaryInfoUseCase {
    override suspend fun execute(params: GetSummaryInfoUseCase.Param): GetSummaryInfoResult {
        val response = transactionsRepository.getTransactionsSummaryInfo(
            query = params.query,
            startDate = params.startDate.toString(),
            endDate = params.endDate.toString(),
            categoryIds = params.categoryIds,
            accountIds = params.accountIds
        )

        return response.fold(
            onSuccess = { GetSummaryInfoResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { GetSummaryInfoResult.NetworkError },
            onElse = { GetSummaryInfoResult.UnknownError(it) }
        )
}
