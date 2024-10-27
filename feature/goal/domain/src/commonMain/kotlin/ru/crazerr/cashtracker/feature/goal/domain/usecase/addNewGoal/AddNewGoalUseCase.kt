package ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.core.utils.exception.fold
import ru.crazerr.cashtracker.feature.goal.domain.repository.GoalsRepository

interface AddNewGoalUseCase : UseCase<AddNewGoalUseCase.Params, AddNewGoalResult> {
    data class Params(
        val name: String,
        val currentAmount: Float,
        val targetAmount: Float,
        val startDate: LocalDate,
        val finalDate: LocalDate,
        val priority: Int,
        val description: String?,
    )
}

internal class AddNewGoalUseCaseImpl(
    private val goalsRepository: GoalsRepository,
) : AddNewGoalUseCase {
    override suspend fun execute(params: AddNewGoalUseCase.Params): AddNewGoalResult {
        val response = goalsRepository.addNewGoal(
            name = params.name,
            currentAmount = params.currentAmount,
            targetAmount = params.targetAmount,
            startDate = params.startDate,
            finalDate = params.finalDate,
            priority = params.priority,
            description = params.description
        )

        return response.fold(
            onSuccess = { AddNewGoalResult.Ok },
            onFailure = { handleResponseThrowable(it) }
        )
    }

    private fun handleResponseThrowable(t: Throwable) =
        t.fold(
            onNetworkError = { AddNewGoalResult.NetworkError },
            onElse = { AddNewGoalResult.UnknownError(it) }
        )
}
