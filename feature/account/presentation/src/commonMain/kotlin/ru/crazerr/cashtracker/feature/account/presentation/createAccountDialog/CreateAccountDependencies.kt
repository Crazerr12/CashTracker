package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog

import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountUseCase
import ru.crazerr.cashtracker.feature.account.domain.usecase.getCurrencies.GetCurrenciesUseCase

data class CreateAccountDependencies(
    val addAccountUseCase: AddAccountUseCase,
    val getCurrenciesUseCase: GetCurrenciesUseCase,
)
