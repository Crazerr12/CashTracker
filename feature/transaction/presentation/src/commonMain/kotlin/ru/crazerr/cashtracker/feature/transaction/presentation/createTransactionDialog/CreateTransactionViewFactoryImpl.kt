package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.ui.CreateTransactionView

internal class CreateTransactionViewFactoryImpl : CreateTransactionViewFactory {
    @Composable
    override fun create(modifier: Modifier, component: CreateTransactionComponent) {
        CreateTransactionView(modifier = modifier, component = component)
    }
}