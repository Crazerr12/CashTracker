package ru.crazerr.cashtracker.feature.goal.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.goal.data.goalDataModule
import ru.crazerr.cashtracker.feature.goal.domain.goalDomainModule
import ru.crazerr.cashtracker.feature.goal.presentation.newGoal.NewGoalComponentFactoryImpl
import ru.crazerr.cashtracker.feature.goal.presentation.newGoal.NewGoalViewFactoryImpl
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentFactory
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalViewFactory

val goalPresentationModule = module {
    single<NewGoalViewFactory> {
        NewGoalViewFactoryImpl()
    }

    single<NewGoalComponentFactory> {
        NewGoalComponentFactoryImpl(addNewGoalUseCase = get())
    }

    includes(goalDomainModule, goalDataModule)
}
