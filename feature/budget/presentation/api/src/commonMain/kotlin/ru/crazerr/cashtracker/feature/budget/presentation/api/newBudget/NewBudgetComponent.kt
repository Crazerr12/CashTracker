package ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponent

abstract class NewBudgetComponent :
    StateHolder<NewBudgetState, NewBudgetViewAction>(InitialNewBudgetState) {
    abstract val dialog: Value<ChildSlot<*, DialogChild>>

    sealed class DialogChild {
        data class NewCategoryDialog(val component: CreateCategoryComponent) : DialogChild()
    }
}
