package ru.crazerr.cashtracker.feature.transaction.domain.api.model

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

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