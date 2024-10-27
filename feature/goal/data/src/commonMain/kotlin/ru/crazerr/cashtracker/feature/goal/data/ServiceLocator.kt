package ru.crazerr.cashtracker.feature.goal.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.goal.data.addNewGoal.dataSource.AddNewGoalLocalDataSource
import ru.crazerr.cashtracker.feature.goal.data.repository.GoalsRepositoryImpl
import ru.crazerr.cashtracker.feature.goal.domain.repository.GoalsRepository

val goalDataModule = module {
    single<GoalsRepository> {
        GoalsRepositoryImpl(
            addNewGoalLocalDataSource = AddNewGoalLocalDataSource(goalDao = get<AppDatabase>().goalDao())
        )
    }
}
