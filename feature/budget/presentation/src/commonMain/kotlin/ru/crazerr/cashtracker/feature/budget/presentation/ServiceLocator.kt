package ru.crazerr.cashtracker.feature.budget.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.budget.data.budgetDataModule
import ru.crazerr.cashtracker.feature.budget.domain.budgetDomainModule
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentFactory
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetViewFactory
import ru.crazerr.cashtracker.feature.budget.presentation.newBudget.NewBudgetComponentFactoryImpl
import ru.crazerr.cashtracker.feature.budget.presentation.newBudget.NewBudgetViewFactoryImpl

val budgetPresentationModule = module {
    single<NewBudgetViewFactory> { NewBudgetViewFactoryImpl() }

    single<NewBudgetComponentFactory> {
        NewBudgetComponentFactoryImpl(
            getCategoriesUseCase = get(),
            addBudgetCategoryUseCase = get(),
            createCategoryComponentFactory = get()
        )
    }

    includes(budgetDomainModule, budgetDataModule)
}
