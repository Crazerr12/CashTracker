package ru.crazerr.cashtracker.feature.transactions.domain.transactions.model

data class CategoryShare(
    val id: Long,
    val name: String,
    val sum: Float,
    val color: Int,
    val iconId: String,
)
