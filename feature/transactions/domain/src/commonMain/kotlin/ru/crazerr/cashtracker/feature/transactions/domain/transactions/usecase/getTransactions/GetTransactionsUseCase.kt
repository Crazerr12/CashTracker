package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions

import kotlinx.coroutines.flow.toList
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Account
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Category
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

interface GetTransactionsUseCase : UseCase<GetTransactionsUseCase.Params, GetTransactionsResult> {
    data class Params(
        val query: String,
        val categories: List<Category>,
        val date: LocalDate,
        val accounts: List<Account>,
    )
}

internal class GetTransactionsUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetTransactionsUseCase {
    override suspend fun execute(params: GetTransactionsUseCase.Params): GetTransactionsResult {
        val response =
            transactionsRepository.getTransactions(
                query = params.query,
                categories = params.categories,
                date = params.date,
                accounts = params.accounts
            )

        return response.fold(
            onSuccess = { GetTransactionsResult.Ok(it.toList()) },
            onFailure = { handleResponse(it) }
        )
    }

    private fun handleResponse(throwable: Throwable): GetTransactionsResult {
        return throwable.fold(
            onNetworkError = { GetTransactionsResult.NetworkError },
            onElse = { GetTransactionsResult.UnknownError(it) }
        )
    }
}