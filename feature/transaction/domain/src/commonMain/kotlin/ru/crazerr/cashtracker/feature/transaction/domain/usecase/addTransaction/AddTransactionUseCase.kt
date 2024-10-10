package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.transaction.domain.repository.TransactionRepository

interface AddTransactionUseCase : UseCase<AddTransactionUseCase.Params, AddTransactionResult> {
    data class Params(
        val name: String,
        val transactionType: TransactionType,
        val amount: Float,
        val categoryId: Long,
        val accountId: Long,
        val date: LocalDate,
        val description: String?,
    )
}

internal class AddTransactionUseCaseImpl(
    private val transactionRepository: TransactionRepository,
) : AddTransactionUseCase {
    override suspend fun execute(params: AddTransactionUseCase.Params): AddTransactionResult {
        val result = transactionRepository.createTransaction(
            name = params.name,
            type = params.transactionType,
            amount = params.amount,
            categoryId = params.categoryId,
            accountId = params.accountId,
            date = params.date,
            description = params.description
        )

        return result.fold(
            onSuccess = { AddTransactionResult.Ok(it) },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(throwable: Throwable) =
        throwable.fold(
            onNetworkError = { AddTransactionResult.NetworkError },
            onElse = { AddTransactionResult.UnknownError(it) }
        )
}