package ru.crazerr.cashtracker.feature.goal.domain.api.model

import kotlinx.datetime.LocalDate

data class Goal(
    val id: Long,
    val name: String,
    val currentAmount: Float,
    val targetAmount: Float,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val priority: Int,
    val description: String?,
)
