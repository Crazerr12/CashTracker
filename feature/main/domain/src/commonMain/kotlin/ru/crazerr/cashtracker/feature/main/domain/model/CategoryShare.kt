package ru.crazerr.cashtracker.feature.main.domain.model

data class CategoryShare(
    val id: Long,
    val name: String,
    val sum: Float,
    val percent: Float,
    val color: Int,
    val iconId: String,
)
