package ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transaction.domain.repository.AccountsRepository

interface GetAccountsUseCase : UseCase<Account, GetAccountsResult>

internal class GetAccountsUseCaseImpl(
    private val accountsRepository: AccountsRepository,
) : GetAccountsUseCase {
    override suspend fun execute(params: Account): GetAccountsResult {
        val result = accountsRepository.getAccounts()

        return result.fold(
            onSuccess = { GetAccountsResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable): GetAccountsResult =
        t.fold(
            onNetworkError = { GetAccountsResult.NetworkError },
            onElse = { GetAccountsResult.UnknownError(it) }
        )
}