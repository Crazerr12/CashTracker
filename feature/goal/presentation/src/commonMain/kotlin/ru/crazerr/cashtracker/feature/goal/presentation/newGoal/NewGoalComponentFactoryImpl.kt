package ru.crazerr.cashtracker.feature.goal.presentation.newGoal

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal.AddNewGoalUseCase
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentAction
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentFactory

internal class NewGoalComponentFactoryImpl(
    private val addNewGoalUseCase: AddNewGoalUseCase,
) : NewGoalComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (NewGoalComponentAction) -> Unit,
    ) = NewGoalComponentImpl(
        componentContext = componentContext,
        onAction = onAction,
        dependencies = NewGoalDependencies(
            addNewGoalUseCase = addNewGoalUseCase,
        )
    )
}
