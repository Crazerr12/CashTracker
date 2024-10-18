package ru.crazerr.cashtracker.feature.account.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.account.data.accountDataModule
import ru.crazerr.cashtracker.feature.account.domain.accountDomainModule
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewFactory
import ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.CreateAccountComponentFactoryImpl
import ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.CreateAccountViewFactoryImpl

val accountPresentationModule = module {
    factory<CreateAccountViewFactory> {
        CreateAccountViewFactoryImpl()
    }

    factory<CreateAccountComponentFactory> {
        CreateAccountComponentFactoryImpl(
            addAccountUseCase = get(),
            getCurrenciesUseCase = get(),
        )
    }

    includes(accountDomainModule, accountDataModule)
}
