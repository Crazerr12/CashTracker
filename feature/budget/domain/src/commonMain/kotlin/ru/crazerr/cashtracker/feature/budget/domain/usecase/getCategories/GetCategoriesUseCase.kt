package ru.crazerr.cashtracker.feature.budget.domain.usecase.getCategories

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.budget.domain.repository.CategoriesRepository

interface GetCategoriesUseCase : UseCase<Unit, GetCategoriesResult>

internal class GetCategoriesUseCaseImpl(
    private val categoriesRepository: CategoriesRepository,
) : GetCategoriesUseCase {
    override suspend fun execute(params: Unit): GetCategoriesResult {
        val response = categoriesRepository.getAllCategories()

        return response.fold(
            onSuccess = { GetCategoriesResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { GetCategoriesResult.NetworkError },
            onElse = { GetCategoriesResult.UnknownError(it) }
        )
}
