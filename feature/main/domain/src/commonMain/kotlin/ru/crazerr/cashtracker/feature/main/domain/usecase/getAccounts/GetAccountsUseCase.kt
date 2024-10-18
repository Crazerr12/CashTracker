package ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.main.domain.repository.AccountsRepository

interface GetAccountsUseCase : UseCase<Unit, GetAccountsResult>

internal class GetAccountsUseCaseImpl(
    private val accountsRepository: AccountsRepository,
) : GetAccountsUseCase {
    override suspend fun execute(params: Unit): GetAccountsResult {
        val response = accountsRepository.getAllAccounts()

        return response.fold(
            onSuccess = { GetAccountsResult.Ok(it) },
            onFailure = { handleResponse(it) }
        )
    }

    private fun handleResponse(throwable: Throwable): GetAccountsResult {
        return throwable.fold(
            onNetworkError = { GetAccountsResult.NetworkError },
            onElse = { GetAccountsResult.UnknownError(it) }
        )
    }
}
