package ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.category.domain.repository.CategoryRepository

interface AddCategoryUseCase : UseCase<Category, AddCategoryResult>

internal class AddCategoryUseCaseImpl(
    private val categoryRepository: CategoryRepository,
) : AddCategoryUseCase {
    override suspend fun execute(params: Category): AddCategoryResult {
        val result = categoryRepository.addCategory(category = params)

        return result.fold(
            onSuccess = { AddCategoryResult.Ok },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable): AddCategoryResult {
        return t.fold(
            onApiError = { AddCategoryResult.ValidationError(it) },
            onNetworkError = { AddCategoryResult.NetworkError },
            onElse = { AddCategoryResult.UnknownError(it) }
        )
    }
}