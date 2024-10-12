package ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions

import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository

interface GetTransactionsUseCase {
    suspend fun execute(): GetTransactionsResult
}

internal class GetTransactionsUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetTransactionsUseCase {
    override suspend fun execute(): GetTransactionsResult {
        val response = transactionsRepository.getMonthlyTransactions()

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
