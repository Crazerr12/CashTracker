package ru.crazerr.cashtracker.feature.account.domain.usecase.getCurrencies

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.account.domain.repository.CurrencyRepository

interface GetCurrenciesUseCase : UseCase<Unit, GetCurrenciesResult>

internal class GetCurrenciesUseCaseImpl(
    private val currencyRepository: CurrencyRepository,
) : GetCurrenciesUseCase {
    override suspend fun execute(params: Unit): GetCurrenciesResult {
        val response = currencyRepository.getCurrencies()

        return response.fold(
            onSuccess = { GetCurrenciesResult.Ok(it) },
            onFailure = ::handleResponseThrowable
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { GetCurrenciesResult.NetworkError },
            onElse = { GetCurrenciesResult.UnknownError(it) }
        )
}
