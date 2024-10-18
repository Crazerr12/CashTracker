package ru.crazerr.cashtracker.feature.category.domain.api.model

data class Category(
    val id: Long,
    val name: String,
    val iconId: String,
    val color: Int,
)
