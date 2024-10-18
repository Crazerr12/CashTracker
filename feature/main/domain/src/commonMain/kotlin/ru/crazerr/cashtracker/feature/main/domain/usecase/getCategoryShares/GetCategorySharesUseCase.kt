package ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.DATE_STRING_END
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.DATE_STRING_START

interface GetCategorySharesUseCase :
    UseCase<GetCategorySharesUseCase.Params, GetCategorySharesResult> {
    data class Params(
        val date: LocalDate,
    )
}

internal class GetCategorySharesUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetCategorySharesUseCase {
    override suspend fun execute(params: GetCategorySharesUseCase.Params): GetCategorySharesResult {
        val response = transactionsRepository.getCategoryShares(
            date = params.date.toString().substring(DATE_STRING_START, DATE_STRING_END)
        )

        return response.fold(
            onSuccess = ::handleOk,
            onFailure = ::handleResponseThrowable
        )
    }

    private fun handleOk(categoryShares: List<CategoryShare>): GetCategorySharesResult {
        val totalAmount = categoryShares.sumOf { it.sum.toDouble() }

        return GetCategorySharesResult.Ok(
            categoryShares.map { it.copy(percent = (it.sum / totalAmount * 100f).toFloat()) }
        )
    }

    private fun handleResponseThrowable(throwable: Throwable) =
        throwable.fold(
            onNetworkError = { GetCategorySharesResult.NetworkError },
            onElse = { GetCategorySharesResult.UnknownError(it) }
        )
}
