package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountUseCase
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentAction
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory

internal class CreateAccountComponentFactoryImpl(
    private val addAccountUseCase: AddAccountUseCase,
) : CreateAccountComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (CreateAccountComponentAction) -> Unit,
    ) = CreateAccountComponentImpl(
        componentContext = componentContext,
        onAction = onAction,
        dependencies = CreateAccountDependencies(
            addAccountUseCase = addAccountUseCase,
        )
    )
}
