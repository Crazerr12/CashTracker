package ru.crazerr.cashtracker.feature.transactions.domain.transactions.model

data class SummaryInfo(
    val income: Float,
    val expenses: Float,
    val mostExpensiveCategories: List<CategoryShare>,
)
