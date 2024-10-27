package ru.crazerr.cashtracker.feature.goal.data.addNewGoal.dataSource

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.goal.GoalDao
import ru.crazerr.cashtracker.core.database.goal.GoalEntity

internal class AddNewGoalLocalDataSource(
    private val goalDao: GoalDao,
) {
    suspend fun addNewGoal(
        name: String,
        currentAmount: Float,
        targetAmount: Float,
        startDate: LocalDate,
        finalDate: LocalDate,
        priority: Int,
        description: String?,
    ): Result<Unit> {
        return try {
            goalDao.insert(
                goalEntity = GoalEntity(
                    name = name,
                    currentAmount = currentAmount,
                    targetAmount = targetAmount,
                    startDate = startDate,
                    finalDate = finalDate,
                    priority = priority,
                    description = description
                )
            )
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
