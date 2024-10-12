package ru.crazerr.cashtracker.feature.main.domain.model

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType

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
