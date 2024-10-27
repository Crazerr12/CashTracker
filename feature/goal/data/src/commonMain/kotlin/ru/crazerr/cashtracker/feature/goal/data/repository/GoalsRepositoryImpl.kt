package ru.crazerr.cashtracker.feature.goal.data.repository

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.feature.goal.data.addNewGoal.dataSource.AddNewGoalLocalDataSource
import ru.crazerr.cashtracker.feature.goal.domain.repository.GoalsRepository

internal class GoalsRepositoryImpl(
    private val addNewGoalLocalDataSource: AddNewGoalLocalDataSource,
) : GoalsRepository {
    override suspend fun addNewGoal(
        name: String,
        currentAmount: Float,
        targetAmount: Float,
        startDate: LocalDate,
        finalDate: LocalDate,
        priority: Int,
        description: String?,
    ): Result<Unit> {
        return addNewGoalLocalDataSource.addNewGoal(
            name = name,
            currentAmount = currentAmount,
            targetAmount = targetAmount,
            startDate = startDate,
            finalDate = finalDate,
            priority = priority,
            description = description
        )
    }
}
