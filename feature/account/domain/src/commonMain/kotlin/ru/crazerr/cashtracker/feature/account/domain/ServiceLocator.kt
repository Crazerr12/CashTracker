package ru.crazerr.cashtracker.feature.account.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountUseCase
import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountUseCaseImpl
import ru.crazerr.cashtracker.feature.account.domain.usecase.getCurrencies.GetCurrenciesUseCase
import ru.crazerr.cashtracker.feature.account.domain.usecase.getCurrencies.GetCurrenciesUseCaseImpl

val accountDomainModule = module {
    factory<AddAccountUseCase> {
        AddAccountUseCaseImpl(
            accountRepository = get()
        )
    }

    factory<GetCurrenciesUseCase> {
        GetCurrenciesUseCaseImpl(
            currencyRepository = get()
        )
    }
}
