package ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponent

abstract class CreateTransactionComponent :
    StateHolder<CreateTransactionState, CreateTransactionViewAction>(InitialCreateTransactionState) {
    abstract val dialog: Value<ChildSlot<*, DialogChild>>

    sealed class DialogChild {
        data class CreateAccountDialog(val component: CreateAccountComponent) :
            DialogChild()

        data class CreateCategoryDialog(val component: CreateCategoryComponent) :
            DialogChild()
    }
}
