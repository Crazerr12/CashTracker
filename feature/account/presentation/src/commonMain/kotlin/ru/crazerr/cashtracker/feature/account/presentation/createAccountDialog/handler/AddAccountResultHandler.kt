package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountResult
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent

internal class AddAccountResultHandler(
    private val result: AddAccountResult,
    private val onAction: (Account) -> Unit,
    private val delegate: CreateAccountComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is AddAccountResult.Ok -> onOk(id = result.id)
            AddAccountResult.NetworkError -> onNetworkError()
            is AddAccountResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(id: Long) {
        delegate.reduceState { copy(buttonLoading = false) }
        onAction(
            Account(
                id = id,
                name = delegate.state.value.name,
                balance = delegate.state.value.balance.toFloat(),
                currency = delegate.state.value.currency,
            )
        )
    }
}
