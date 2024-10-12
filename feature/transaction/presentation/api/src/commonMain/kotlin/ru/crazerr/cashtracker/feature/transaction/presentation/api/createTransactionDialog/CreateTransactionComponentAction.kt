package ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog

import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

sealed interface CreateTransactionComponentAction {
    data class TransactionCreated(val transaction: Transaction) : CreateTransactionComponentAction
    data object Canceled : CreateTransactionComponentAction
}
