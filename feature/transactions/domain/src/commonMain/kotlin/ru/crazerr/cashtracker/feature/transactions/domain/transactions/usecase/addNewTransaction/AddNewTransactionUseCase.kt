package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.addNewTransaction

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

typealias AddNewTransactionUseCase = UseCase<Transaction, AddNewTransactionResult>

internal class AddNewTransactionUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : AddNewTransactionUseCase {
    override suspend fun execute(params: Transaction): AddNewTransactionResult {
        val response = transactionsRepository.createNewTransaction(params)

        return response.fold(
            onSuccess = { AddNewTransactionResult.Ok },
            onFailure = { handleResponse(throwable = it) }
        )
    }

    private fun handleResponse(throwable: Throwable): AddNewTransactionResult {
        return throwable.fold(
            onNetworkError = { AddNewTransactionResult.NetworkError },
            onElse = { AddNewTransactionResult.UnknownError(it) },
        )
    }
}