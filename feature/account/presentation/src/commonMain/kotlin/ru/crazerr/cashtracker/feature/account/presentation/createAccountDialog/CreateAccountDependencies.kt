package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog

import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountUseCase

data class CreateAccountDependencies(
    val addAccountUseCase: AddAccountUseCase,
)
