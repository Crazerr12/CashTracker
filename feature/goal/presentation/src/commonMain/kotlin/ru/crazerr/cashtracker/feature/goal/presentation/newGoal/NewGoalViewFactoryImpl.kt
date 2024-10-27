package ru.crazerr.cashtracker.feature.goal.presentation.newGoal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.crazerr.cashtracker.feature.goal.presentation.newGoal.ui.NewGoalView
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponent
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalViewFactory

internal class NewGoalViewFactoryImpl : NewGoalViewFactory {
    @Composable
    override fun create(modifier: Modifier, component: NewGoalComponent) {
        NewGoalView(modifier = modifier, component = component)
    }
}
