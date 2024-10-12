package ru.crazerr.cashtracker.feature.transactions.domain.transactions.model

import ru.crazerr.cashtracker.core.utils.model.TransactionType
import java.time.LocalDate

data class Transaction(
    val id: Long,
    val name: String,
    val amount: Float,
    val type: TransactionType,
    val date: LocalDate,
    val category: Category,
    val account: Account,
    val description: String?,
)
