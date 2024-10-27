package ru.crazerr.cashtracker.feature.goal.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal.AddNewGoalUseCase
import ru.crazerr.cashtracker.feature.goal.domain.usecase.addNewGoal.AddNewGoalUseCaseImpl

val goalDomainModule = module {
    factory<AddNewGoalUseCase> { AddNewGoalUseCaseImpl(goalsRepository = get()) }
}
