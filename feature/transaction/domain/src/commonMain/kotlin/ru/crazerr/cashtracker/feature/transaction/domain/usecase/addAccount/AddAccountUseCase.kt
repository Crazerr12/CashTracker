package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addAccount

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transaction.domain.repository.AccountsRepository

interface AddAccountUseCase : UseCase<Account, AddAccountResult>

internal class AddAccountUseCaseImpl(
    private val accountsRepository: AccountsRepository,
) : AddAccountUseCase {
    override suspend fun execute(params: Account): AddAccountResult {
        val result = accountsRepository.addAccount(account = params)

        return result.fold(
            onSuccess = { AddAccountResult.Ok },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onApiError = { AddAccountResult.ValidationError(it) },
            onNetworkError = { AddAccountResult.NetworkError },
            onElse = { AddAccountResult.UnknownError(it) }
        )
}