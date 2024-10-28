package ru.crazerr.cashtracker.feature.budgets.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.budgets.data.budgetsDataModule
import ru.crazerr.cashtracker.feature.budgets.domain.budgetsDomainModule
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponentFactory
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponentFactoryImpl
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsViewFactory
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsViewFactoryImpl
import ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory.BudgetsStoryComponentFactory
import ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory.BudgetsStoryComponentFactoryImpl

val budgetsPresentationModule = module {
    single<BudgetsViewFactory> { BudgetsViewFactoryImpl() }

    single<BudgetsComponentFactory> {
        BudgetsComponentFactoryImpl(
            getBudgetsUseCase = get(),
            newBudgetComponentFactory = get(),
            deleteBudgetUseCase = get()
        )
    }

    single<BudgetsStoryComponentFactory> {
        BudgetsStoryComponentFactoryImpl(budgetsComponentFactory = get())
    }

    includes(budgetsDomainModule, budgetsDataModule)
}
