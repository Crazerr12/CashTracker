package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getAccounts

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.AccountsRepository

interface GetAccountsUseCase : UseCase<Unit, GetAccountsResult>

internal class GetAccountUseCaseImpl(
    private val accountsRepository: AccountsRepository,
) : GetAccountsUseCase {
    override suspend fun execute(params: Unit): GetAccountsResult {
        val response = accountsRepository.getAccounts()

        return response.fold(
            onSuccess = { GetAccountsResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { GetAccountsResult.NetworkError },
            onElse = { GetAccountsResult.UnknownError(it) }
        )
}
