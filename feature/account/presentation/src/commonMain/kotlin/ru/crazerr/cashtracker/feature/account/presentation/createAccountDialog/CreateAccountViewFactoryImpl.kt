package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewFactory
import ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.ui.CreateAccountView

internal class CreateAccountViewFactoryImpl : CreateAccountViewFactory {
    @Composable
    override fun create(modifier: Modifier, component: CreateAccountComponent) {
        CreateAccountView(modifier = modifier, component = component)
    }
}
