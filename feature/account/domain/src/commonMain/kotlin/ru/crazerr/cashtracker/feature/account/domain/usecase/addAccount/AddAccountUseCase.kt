package ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.account.domain.repository.AccountRepository

interface AddAccountUseCase : UseCase<AddAccountUseCase.Params, AddAccountResult> {
    data class Params(
        val name: String,
        val currencyId: Long,
        val balance: Float,
    )
}

internal class AddAccountUseCaseImpl(
    private val accountRepository: AccountRepository,
) : AddAccountUseCase {
    override suspend fun execute(params: AddAccountUseCase.Params): AddAccountResult {
        val result = accountRepository.addAccount(
            name = params.name,
            balance = params.balance,
            currencyId = params.currencyId
        )

        return result.fold(
            onSuccess = { AddAccountResult.Ok(id = it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { AddAccountResult.NetworkError },
            onElse = { AddAccountResult.UnknownError(it) }
        )
}
