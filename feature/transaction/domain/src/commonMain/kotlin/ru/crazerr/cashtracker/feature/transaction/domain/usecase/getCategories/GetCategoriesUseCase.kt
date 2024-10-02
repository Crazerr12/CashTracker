package ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.transaction.domain.repository.CategoriesRepository

interface GetCategoriesUseCase : UseCase<Unit, GetCategoriesResult>

internal class GetCategoriesUseCaseImpl(
    private val categoriesRepository: CategoriesRepository,
) : GetCategoriesUseCase {
    override suspend fun execute(params: Unit): GetCategoriesResult {
        val result = categoriesRepository.getCategories()

        return result.fold(
            onSuccess = { GetCategoriesResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(throwable: Throwable) =
        throwable.fold(
            onNetworkError = { GetCategoriesResult.NetworkError },
            onElse = { GetCategoriesResult.UnknownError(it) }
        )
}