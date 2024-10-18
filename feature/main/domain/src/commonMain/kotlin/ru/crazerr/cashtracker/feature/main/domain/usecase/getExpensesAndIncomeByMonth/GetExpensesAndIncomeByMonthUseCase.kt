package ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.DATE_STRING_END
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.DATE_STRING_START

interface GetExpensesAndIncomeByMonthUseCase :
    UseCase<GetExpensesAndIncomeByMonthUseCase.Params, GetExpensesAndIncomeByMonthResult> {
    data class Params(
        val date: LocalDate,
    )
}

internal class GetExpensesAndIncomeByMonthUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetExpensesAndIncomeByMonthUseCase {
    override suspend fun execute(params: GetExpensesAndIncomeByMonthUseCase.Params): GetExpensesAndIncomeByMonthResult {
        val response = transactionsRepository.getExpensesAndIncomeByMonth(
            date = params.date.toString().substring(DATE_STRING_START, DATE_STRING_END),
        )

        return response.fold(
            onSuccess = { GetExpensesAndIncomeByMonthResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { GetExpensesAndIncomeByMonthResult.NetworkError },
            onElse = { GetExpensesAndIncomeByMonthResult.UnknownError(it) }
        )
}
