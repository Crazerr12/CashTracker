package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.dateTime.fromInput
import ru.crazerr.cashtracker.core.utils.dateTime.toInput
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.core.utils.toDateFormat
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentAction
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentAction
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction.AddTransactionUseCase
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentAction
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewAction
import ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.handler.AddTransactionResultHandler
import ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.handler.GetAccountsResultHandler
import ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.handler.GetCategoriesResultHandler

internal class CreateTransactionComponentImpl(
    componentContext: ComponentContext,
    private val onAction: (CreateTransactionComponentAction) -> Unit,
    private val dependencies: CreateTransactionDependencies,
) : CreateTransactionComponent(), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()

    private val dialogNavigation = SlotNavigation<DialogConfig>()

    override val dialog: Value<ChildSlot<*, DialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::dialogChild
    )

    init {
        doOnCreate {
            coroutineScope.launch {
                val categoriesResult =
                    async(Dispatchers.IO) { dependencies.getCategoriesUseCase.execute(Unit) }
                val accountsResult =
                    async(Dispatchers.IO) { dependencies.getAccountsUseCase.execute(Unit) }

                GetCategoriesResultHandler(
                    result = categoriesResult.await(),
                    delegate = this@CreateTransactionComponentImpl
                ).handle()

                GetAccountsResultHandler(
                    result = accountsResult.await(),
                    delegate = this@CreateTransactionComponentImpl
                ).handle()
            }
        }
    }

    override fun obtainViewAction(action: CreateTransactionViewAction) {
        when (action) {
            is CreateTransactionViewAction.AmountChange -> onAmountChange(amount = action.amount)
            CreateTransactionViewAction.CancelClick -> onCancelClick()
            CreateTransactionViewAction.CreateNewAccount -> onCreateNewAccount()
            CreateTransactionViewAction.CreateNewCategory -> onCreateNewCategory()
            is CreateTransactionViewAction.SetDate -> onSetDate(date = action.date)
            is CreateTransactionViewAction.DescriptionChange -> onDescriptionChange(description = action.description)
            is CreateTransactionViewAction.ManageAccountsDropdownMenu -> onManageAccountDropdownMenu()
            is CreateTransactionViewAction.ManageCategoriesDropdownMenu -> onManageCategoriesDropdownMenu()
            is CreateTransactionViewAction.NameChange -> onNameChange(name = action.name)
            CreateTransactionViewAction.SaveTransactionClick -> onSaveTransactionClick()
            is CreateTransactionViewAction.SelectAccount -> onSelectAccount(account = action.account)
            is CreateTransactionViewAction.SelectCategory -> onSelectCategory(category = action.category)
            is CreateTransactionViewAction.SetTransactionType -> onSetTransactionType(
                transactionType = action.transactionType,
            )

            CreateTransactionViewAction.ManageDatePicker -> onManageDatePicker()
            is CreateTransactionViewAction.UpdateDateString -> onUpdateDateString(dateString = action.dateString)
        }
    }

    private fun dialogChild(config: DialogConfig, componentContext: ComponentContext): DialogChild {
        return when (config) {
            DialogConfig.CreateAccount -> createAccountDialog(componentContext = componentContext)
            DialogConfig.CreateCategory -> createCategoryDialog(componentContext = componentContext)
        }
    }

    private fun createCategoryDialog(componentContext: ComponentContext): DialogChild.CreateCategoryDialog =
        DialogChild.CreateCategoryDialog(
            component = dependencies.createCategoryComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        CreateCategoryComponentAction.Canceled -> dialogNavigation.dismiss()
                        is CreateCategoryComponentAction.CategoryCreated -> categoryCreated(category = action.category)
                    }
                }
            )
        )

    private fun createAccountDialog(componentContext: ComponentContext): DialogChild.CreateAccountDialog =
        DialogChild.CreateAccountDialog(
            dependencies.createAccountComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        is CreateAccountComponentAction.AccountCreated -> accountCreated(account = action.account)

                        CreateAccountComponentAction.Canceled -> dialogNavigation.dismiss()
                    }
                }
            )
        )

    private fun onAmountChange(amount: String) {
        reduceState {
            if (amount.last().isDigit()) {
                copy(amount = amount, amountError = null)

            } else {
                copy(amountError = "Поле принимает только цифры")
            }
        }
    }

    private fun onCancelClick() {
        onAction(CreateTransactionComponentAction.Canceled)
    }

    private fun onCreateNewAccount() {
        dialogNavigation.activate(DialogConfig.CreateAccount)
    }

    private fun onCreateNewCategory() {
        dialogNavigation.activate(DialogConfig.CreateCategory)
    }

    private fun onSetDate(date: LocalDate) {
        reduceState { copy(date = date, dateString = date.toInput()) }
    }

    private fun onDescriptionChange(description: String) {
        reduceState { copy(description = description.ifEmpty { null }) }
    }

    private fun onManageAccountDropdownMenu() {
        if (state.value.accounts.isNotEmpty()) {
            reduceState { copy(accountsIsExpand = !accountsIsExpand) }
        }
    }

    private fun onManageCategoriesDropdownMenu() {
        if (state.value.categories.isNotEmpty()) {
            reduceState { copy(categoriesIsExpand = !categoriesIsExpand) }
        }
    }

    private fun onNameChange(name: String) {
        reduceState { copy(name = name) }
    }

    private fun onSaveTransactionClick() {
        if (state.value.amountError.isNullOrEmpty()) {
            coroutineScope.launch {
                val result = dependencies.addTransactionUseCase.execute(
                    params = AddTransactionUseCase.Params(
                        name = state.value.name,
                        transactionType = state.value.transactionType,
                        amount = state.value.amount.toFloat(),
                        categoryId = state.value.selectedCategory.id,
                        accountId = state.value.selectedAccount.id,
                        date = state.value.date,
                        description = state.value.description
                    )
                )

                AddTransactionResultHandler(
                    result = result,
                    delegate = this@CreateTransactionComponentImpl,
                    onAction = { onAction(CreateTransactionComponentAction.TransactionCreated(it)) }
                )
            }
        }
    }

    private fun onSelectAccount(account: Account) {
        reduceState { copy(selectedAccount = account, accountsIsExpand = false) }
    }

    private fun onSelectCategory(category: Category) {
        reduceState { copy(selectedCategory = category, categoriesIsExpand = false) }
    }

    private fun onSetTransactionType(transactionType: TransactionType) {
        reduceState { copy(transactionType = transactionType) }
    }

    private fun accountCreated(account: Account) {
        dialogNavigation.dismiss()
        reduceState {
            copy(
                selectedAccount = account
            )
        }
    }

    private fun categoryCreated(category: Category) {
        dialogNavigation.dismiss()
        reduceState {
            copy(
                selectedCategory = category
            )
        }
    }

    private fun onManageDatePicker() {
        reduceState { copy(showDatePicker = !showDatePicker) }
    }

    private fun onUpdateDateString(dateString: String) {
        if (dateString.all { it.isDigit() || it == '.' } && dateString.length <= 10) {
            reduceState { copy(dateString = dateString.toDateFormat()) }

            if (dateString.length == 10) {
                reduceState { copy(date = dateString.fromInput()) }
            }
        }
    }

    @Serializable
    private sealed interface DialogConfig {
        data object CreateAccount : DialogConfig
        data object CreateCategory : DialogConfig
    }
}