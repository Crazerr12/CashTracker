package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount.AddAccountUseCase
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentAction
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountViewAction
import ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.handler.AddAccountResultHandler
import ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.handler.GetCurrenciesResultHandler

internal class CreateAccountComponentImpl(
    componentContext: ComponentContext,
    private val onAction: (CreateAccountComponentAction) -> Unit,
    private val dependencies: CreateAccountDependencies,
) : CreateAccountComponent(), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()

    init {
        coroutineScope.launch {
            val result = dependencies.getCurrenciesUseCase.execute(Unit)

            GetCurrenciesResultHandler(
                result = result,
                delegate = this@CreateAccountComponentImpl
            ).handle()
        }
    }

    override fun obtainViewAction(action: CreateAccountViewAction) {
        when (action) {
            is CreateAccountViewAction.UpdateName -> onUpdateName(name = action.name)
            is CreateAccountViewAction.UpdateCurrentAmount -> onUpdateCurrentAmount(amount = action.amount)
            is CreateAccountViewAction.UpdateCurrency -> onUpdateCurrency(currency = action.currency)
            is CreateAccountViewAction.UpdateDescription -> onUpdateDescription(description = action.description)
            CreateAccountViewAction.CancelClick -> onCancelClick()
            CreateAccountViewAction.SaveClick -> onCreateNewAccountClick()
            CreateAccountViewAction.ManageCurrencyMenu -> onManageCurrencyMenu()
        }
    }

    private fun onUpdateName(name: String) {
        reduceState { copy(name = name) }
    }

    private fun onUpdateCurrentAmount(amount: String) {
        reduceState {
            if (amount.lastOrNull()?.isDigit() == true || amount.isEmpty()) {
                copy(balance = amount, balanceError = null)
            } else {
                copy(balanceError = "Поле принимает только цифры")
            }
        }
    }

    private fun onUpdateCurrency(currency: Currency) {
        reduceState { copy(selectedCurrency = currency, isExpanded = false) }
    }

    private fun onUpdateDescription(description: String) {
        reduceState { copy(description = description) }
    }

    private fun onCancelClick() {
        onAction(CreateAccountComponentAction.Canceled)
    }

    private fun onCreateNewAccountClick() {
        if (state.value.balanceError.isNullOrEmpty()) {
            reduceState { copy(buttonLoading = true) }
            coroutineScope.launch {
                val result = dependencies.addAccountUseCase.execute(
                    params = AddAccountUseCase.Params(
                        name = state.value.name,
                        balance = state.value.balance.toFloat(),
                        currencyId = state.value.selectedCurrency.id,
                    )
                )

                AddAccountResultHandler(
                    result = result,
                    onAction = { onAction(CreateAccountComponentAction.AccountCreated(it)) },
                    delegate = this@CreateAccountComponentImpl
                ).handle()
            }
        }
    }

    private fun onManageCurrencyMenu() {
        reduceState { copy(isExpanded = !isExpanded) }
    }
}
