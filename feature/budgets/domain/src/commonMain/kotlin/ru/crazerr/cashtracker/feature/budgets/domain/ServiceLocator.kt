package ru.crazerr.cashtracker.feature.budgets.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById.DeleteBudgetUseCase
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById.DeleteBudgetUseCaseImpl
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets.GetBudgetsUseCase
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets.GetBudgetsUseCaseImpl

val budgetsDomainModule = module {
    factory<GetBudgetsUseCase> { GetBudgetsUseCaseImpl(budgetsRepository = get()) }
    factory<DeleteBudgetUseCase> { DeleteBudgetUseCaseImpl(budgetsRepository = get()) }
}
