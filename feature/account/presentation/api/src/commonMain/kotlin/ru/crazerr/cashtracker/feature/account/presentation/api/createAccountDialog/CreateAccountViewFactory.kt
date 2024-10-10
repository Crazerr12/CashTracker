package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface CreateAccountViewFactory {
    @Composable
    fun create(modifier: Modifier, component: CreateAccountComponent)
}