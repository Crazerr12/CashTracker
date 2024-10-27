package ru.crazerr.cashtracker.feature.goal.presentation.newGoal

import ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal.AddNewGoalUseCase

class NewGoalDependencies(
    val addNewGoalUseCase: AddNewGoalUseCase,
)
