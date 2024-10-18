package ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository

internal const val DATE_STRING_START = 0
internal const val DATE_STRING_END = 7

interface GetTransactionsUseCase : UseCase<GetTransactionsUseCase.Params, GetTransactionsResult> {

    data class Params(
        val date: LocalDate,
        val limit: Int,
    )
}

internal class GetTransactionsUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetTransactionsUseCase {
    override suspend fun execute(params: GetTransactionsUseCase.Params): GetTransactionsResult {
        val response =
            transactionsRepository.getMonthlyTransactions(
                date = params.date.toString().substring(DATE_STRING_START, DATE_STRING_END),
                limit = params.limit
            )

        return response.fold(
            onSuccess = { GetTransactionsResult.Ok(it) },
            onFailure = { handleResponse(it) }
        )
    }

    private fun handleResponse(throwable: Throwable): GetTransactionsResult {
        return throwable.fold(
            onNetworkError = {
                GetTransactionsResult.NetworkError
            },
            onElse = {
                GetTransactionsResult.UnknownError(it)
            }
        )
    }
}
