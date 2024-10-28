package ru.crazerr.cashtracker.feature.budgets.presentation.budgets

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentAction
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById.DeleteBudgetUseCase
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.handler.DeleteBudgetResultHandler
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.handler.GetBudgetsResultHandler

abstract class BudgetsComponent :
    StateHolder<BudgetsState, BudgetsViewAction>(InitialBudgetsState) {

    abstract val dialog: Value<ChildSlot<*, DialogChild>>

    sealed class DialogChild {
        data class NewBudgetDialogChild(val component: NewBudgetComponent) : DialogChild()
    }
}

class BudgetsComponentImpl(
    componentContext: ComponentContext,
    private val onAction: (BudgetsComponentAction) -> Unit,
    private val dependencies: BudgetsDependencies,
) : BudgetsComponent(), ComponentContext by componentContext {

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
            getBudgets()
        }
    }

    override fun obtainViewAction(action: BudgetsViewAction) {
        when (action) {
            BudgetsViewAction.AddNewBudgetClick -> onAddNewBudgetClick()
            is BudgetsViewAction.DeleteBudgetClick -> onDeleteBudgetClick(id = action.id)
            is BudgetsViewAction.EditBudgetClick -> onEditBudgetClick(id = action.id)
        }
    }

    private fun dialogChild(config: DialogConfig, componentContext: ComponentContext): DialogChild =
        when (config) {
            DialogConfig.NewBudgetConfig -> createNewBudgetDialog(componentContext = componentContext)
        }

    private fun createNewBudgetDialog(componentContext: ComponentContext): DialogChild.NewBudgetDialogChild =
        DialogChild.NewBudgetDialogChild(
            component = dependencies.newBudgetComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        NewBudgetComponentAction.BudgetCreated -> {
                            getBudgets()
                            dialogNavigation.dismiss()
                        }

                        NewBudgetComponentAction.Canceled -> dialogNavigation.dismiss()
                    }
                }
            )
        )

    private fun onEditBudgetClick(id: Long) {
        coroutineScope.launch {
        }
    }

    private fun onDeleteBudgetClick(id: Long) {
        coroutineScope.launch {
            val result = dependencies.deleteBudgetUseCase.execute(
                params = DeleteBudgetUseCase.Params(budgetId = id)
            )

            DeleteBudgetResultHandler(
                result = result,
                delegate = this@BudgetsComponentImpl
            ).handle()
        }
    }

    private fun getBudgets() {
        coroutineScope.launch {
            val result = dependencies.getBudgetsUseCase.execute(Unit)

            GetBudgetsResultHandler(
                result = result,
                delegate = this@BudgetsComponentImpl
            ).handle()
        }
    }

    private fun onAddNewBudgetClick() {
        dialogNavigation.activate(DialogConfig.NewBudgetConfig)
    }

    @Serializable
    private sealed interface DialogConfig {
        data object NewBudgetConfig : DialogConfig
    }
}
