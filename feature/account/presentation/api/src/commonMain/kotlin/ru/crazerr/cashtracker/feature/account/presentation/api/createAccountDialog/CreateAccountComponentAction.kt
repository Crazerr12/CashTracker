package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

sealed interface CreateAccountComponentAction {
    data class AccountCreated(val account: Account): CreateAccountComponentAction
    data object Canceled: CreateAccountComponentAction
}