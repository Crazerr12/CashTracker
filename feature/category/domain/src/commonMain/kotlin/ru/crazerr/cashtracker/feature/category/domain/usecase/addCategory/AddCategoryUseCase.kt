package ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.category.domain.repository.CategoryRepository

interface AddCategoryUseCase : UseCase<AddCategoryUseCase.Params, AddCategoryResult> {
    data class Params(
        val name: String,
        val color: Int,
        val iconId: String,
    )
}

internal class AddCategoryUseCaseImpl(
    private val categoryRepository: CategoryRepository,
) : AddCategoryUseCase {
    override suspend fun execute(params: AddCategoryUseCase.Params): AddCategoryResult {
        val result = categoryRepository.addCategory(
            name = params.name,
            color = params.color,
            iconId = params.iconId
        )

        return result.fold(
            onSuccess = { AddCategoryResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable): AddCategoryResult {
        return t.fold(
            onNetworkError = { AddCategoryResult.NetworkError },
            onElse = { AddCategoryResult.UnknownError(it) }
        )
    }
}
