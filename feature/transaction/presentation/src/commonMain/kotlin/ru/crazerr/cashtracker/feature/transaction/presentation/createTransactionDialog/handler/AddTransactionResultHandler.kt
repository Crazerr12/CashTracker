package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction.AddTransactionResult
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent

internal class AddTransactionResultHandler(
    private val result: AddTransactionResult,
    private val delegate: CreateTransactionComponent,
    private val onAction: (Transaction) -> Unit,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is AddTransactionResult.Ok -> onOk(id = result.id)
            AddTransactionResult.NetworkError -> onNetworkError()
            is AddTransactionResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(id: Long) {
        onAction(
            Transaction(
                id = id,
                name = delegate.state.value.name,
                amount = delegate.state.value.amount.toFloat(),
                type = delegate.state.value.transactionType,
                date = delegate.state.value.date,
                category = delegate.state.value.selectedCategory,
                account = delegate.state.value.selectedAccount,
                description = delegate.state.value.description
            )
        )
    }
}