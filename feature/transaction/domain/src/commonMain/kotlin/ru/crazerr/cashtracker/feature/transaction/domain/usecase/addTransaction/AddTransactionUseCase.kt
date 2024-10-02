package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transaction.domain.repository.TransactionRepository

interface AddTransactionUseCase : UseCase<Transaction, AddTransactionResult>

internal class AddTransactionUseCaseImpl(
    private val transactionRepository: TransactionRepository,
) : AddTransactionUseCase {
    override suspend fun execute(params: Transaction): AddTransactionResult {
        val result = transactionRepository.createTransaction(transaction = params)

        return result.fold(
            onSuccess = { AddTransactionResult.Ok },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(throwable: Throwable) =
        throwable.fold(
            onApiError = { AddTransactionResult.ValidationError(it) },
            onNetworkError = { AddTransactionResult.NetworkError },
            onElse = { AddTransactionResult.UnknownError(it) }
        )
}