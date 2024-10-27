package ru.crazerr.cashtracker.core.database.transaction.model

import androidx.room.ColumnInfo
import kotlinx.datetime.LocalDate

data class BudgetCategoryStats(
    @ColumnInfo(name = "current_amount") val currentAmount: Float,
    @ColumnInfo(name = "last_transaction_date") val lastTransactionDate: LocalDate,
)
