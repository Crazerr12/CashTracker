package ru.crazerr.cashtracker.feature.goal.domain.repository

import kotlinx.datetime.LocalDate

interface GoalsRepository {
    suspend fun addNewGoal(
        name: String,
        currentAmount: Float,
        targetAmount: Float,
        startDate: LocalDate,
        finalDate: LocalDate,
        priority: Int,
        description: String?,
    ): Result<Unit>
}
