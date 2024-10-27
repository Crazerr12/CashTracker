package ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.budget.domain.repository.BudgetCategoriesRepository

interface AddBudgetCategoryUseCase :
    UseCase<AddBudgetCategoryUseCase.Params, AddBudgetCategoryResult> {
    data class Params(
        val categoryId: Long,
        val maxAmount: Float,
        val isRegular: Boolean,
        val nextTransactionDate: LocalDate?,
    )
}

internal class AddBudgetCategoryUseCaseImpl(
    private val budgetCategoriesRepository: BudgetCategoriesRepository,
) : AddBudgetCategoryUseCase {
    override suspend fun execute(params: AddBudgetCategoryUseCase.Params): AddBudgetCategoryResult {
        val response =
            budgetCategoriesRepository.addBudgetCategory(
                categoryId = params.categoryId,
                maxAmount = params.maxAmount,
                isRegular = params.isRegular,
                nextCreationDate = params.nextTransactionDate,
            )

        return response.fold(
            onSuccess = { AddBudgetCategoryResult.Ok },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { AddBudgetCategoryResult.NetworkError },
            onElse = { AddBudgetCategoryResult.UnknownError(it) }
        )
}
