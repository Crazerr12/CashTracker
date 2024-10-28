package ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.budgets.domain.repository.BudgetsRepository

interface DeleteBudgetUseCase : UseCase<DeleteBudgetUseCase.Params, DeleteBudgetResult> {
    data class Params(
        val budgetId: Long,
    )
}

internal class DeleteBudgetUseCaseImpl(
    private val budgetsRepository: BudgetsRepository,
) : DeleteBudgetUseCase {
    override suspend fun execute(params: DeleteBudgetUseCase.Params): DeleteBudgetResult {
        val result = budgetsRepository.deleteBudgetById(budgetId = params.budgetId)

        return result.fold(
            onSuccess = { DeleteBudgetResult.Ok(params.budgetId) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { DeleteBudgetResult.NetworkError },
            onElse = { DeleteBudgetResult.UnknownError(it) }
        )
}
