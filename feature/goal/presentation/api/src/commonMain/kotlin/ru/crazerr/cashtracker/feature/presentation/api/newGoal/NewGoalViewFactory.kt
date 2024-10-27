package ru.crazerr.cashtracker.feature.presentation.api.newGoal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface NewGoalViewFactory {
    @Composable
    fun create(modifier: Modifier, component: NewGoalComponent)
}
