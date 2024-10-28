package ru.crazerr.cashtracker.core.mediator

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.account.presentation.accountPresentationModule
import ru.crazerr.cashtracker.feature.budget.presentation.budgetPresentationModule
import ru.crazerr.cashtracker.feature.budgets.presentation.budgetsPresentationModule
import ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory.BudgetsStoryComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.categoryPresentationModule
import ru.crazerr.cashtracker.feature.goal.presentation.goalPresentationModule
import ru.crazerr.cashtracker.feature.main.presentation.mainPresentationModule
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.MainStoryComponentFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.transactionPresentationModule
import ru.crazerr.cashtracker.feature.transactions.presentation.transactionsPresentationModule
import ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory.TransactionsStoryComponentFactory

val mediatorModule = module {
    single<RootComponent.Factory> {
        RootComponentImpl.FactoryImpl()
    }
}

internal val internalModule = module {
    includes(transactionPresentationModule)
    includes(categoryPresentationModule)
    includes(accountPresentationModule)
    includes(mainPresentationModule)
    includes(transactionsPresentationModule)
    includes(budgetPresentationModule)
    includes(goalPresentationModule)
    includes(budgetsPresentationModule)
}

internal class InternalComponent : KoinComponent {
    val mainStoryComponentFactory: MainStoryComponentFactory by inject()
    val transactionsStoryComponentFactory: TransactionsStoryComponentFactory by inject()
    val budgetsStoryComponentFactory: BudgetsStoryComponentFactory by inject()

    companion object {
        fun create(): InternalComponent {
            loadKoinModules(internalModule)
            return InternalComponent()
        }
    }
}
