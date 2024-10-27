package ru.crazerr.cashtracker.feature.presentation.api.newGoal

import ru.crazerr.cashtracker.core.utils.presentation.StateHolder

abstract class NewGoalComponent :
    StateHolder<NewGoalState, NewGoalViewAction>(InitialNewGoalState)
