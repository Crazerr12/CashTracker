package ru.crazerr.cashtracker.feature.main.presentation.main

import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase

class MainDependencies(
    val getTransactionsUseCase: GetTransactionsUseCase,
    val getAccountsUseCase: GetAccountsUseCase,
)