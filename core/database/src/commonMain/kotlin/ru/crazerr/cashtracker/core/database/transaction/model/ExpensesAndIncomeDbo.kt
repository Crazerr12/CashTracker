package ru.crazerr.cashtracker.core.database.transaction.model

import androidx.room.ColumnInfo

data class ExpensesAndIncomeDbo(
    @ColumnInfo(name = "total_expenses") val totalExpenses: Float,
    @ColumnInfo(name = "total_income") val totalIncome: Float,
)
