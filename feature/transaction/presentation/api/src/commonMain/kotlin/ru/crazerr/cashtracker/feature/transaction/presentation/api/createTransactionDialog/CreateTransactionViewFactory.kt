package ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface CreateTransactionViewFactory {
    @Composable
    fun create(modifier: Modifier, component: CreateTransactionComponent)
}
