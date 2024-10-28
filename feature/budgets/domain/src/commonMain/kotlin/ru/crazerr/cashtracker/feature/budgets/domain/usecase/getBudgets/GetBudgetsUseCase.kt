package ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.budgets.domain.repository.BudgetsRepository

interface GetBudgetsUseCase : UseCase<Unit, GetBudgetsResult>

internal class GetBudgetsUseCaseImpl(
    private val budgetsRepository: BudgetsRepository,
) : GetBudgetsUseCase {
    override suspend fun execute(params: Unit): GetBudgetsResult {
        val result = budgetsRepository.getAllBudgets()

        return result.fold(
            onSuccess = { GetBudgetsResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { GetBudgetsResult.NetworkError },
            onElse = { GetBudgetsResult.UnknownError(it) }
        )
}
