package ru.crazerr.cashtracker.feature.budget.presentation.newBudget

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.core.utils.dateTime.atStartOfMonth
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory.AddBudgetCategoryUseCase
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentAction
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetViewAction
import ru.crazerr.cashtracker.feature.budget.presentation.newBudget.handler.AddBudgetCategoryResultHandler
import ru.crazerr.cashtracker.feature.budget.presentation.newBudget.handler.GetCategoriesResultHandler
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentAction

internal class NewBudgetComponentImpl(
    componentContext: ComponentContext,
    private val onAction: (NewBudgetComponentAction) -> Unit,
    private val dependencies: NewBudgetDependencies,
) : NewBudgetComponent(), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()

    private val dialogNavigation = SlotNavigation<DialogConfig>()

    override val dialog: Value<ChildSlot<*, DialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::dialogChild
    )

    init {
        getCategories()
    }

    override fun obtainViewAction(action: NewBudgetViewAction) {
        when (action) {
            NewBudgetViewAction.AddNewCategoryClick -> onAddNewCategoryClick()
            is NewBudgetViewAction.AmountChange -> onAmountChange(amount = action.amount)
            NewBudgetViewAction.CancelClick -> onCancelClick()
            is NewBudgetViewAction.CategorySelect -> onCategorySelect(category = action.category)
            NewBudgetViewAction.ManageCategoryDropdown -> onManageCategoryDropdown()
            NewBudgetViewAction.RegularCheckboxClick -> onRegularClick()
            is NewBudgetViewAction.UpdateCategorySearch -> onUpdateCategorySearch(search = action.search)
            NewBudgetViewAction.CreateBudgetCategoryClick -> onCreateBudgetCategoryClick()
        }
    }

    private fun dialogChild(config: DialogConfig, componentContext: ComponentContext): DialogChild {
        return when (config) {
            DialogConfig.CreateCategory -> categoryChild(componentContext = componentContext)
        }
    }

    private fun categoryChild(componentContext: ComponentContext): DialogChild.NewCategoryDialog =
        DialogChild.NewCategoryDialog(
            component = dependencies.createCategoryComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        CreateCategoryComponentAction.Canceled -> dialogNavigation.dismiss()
                        is CreateCategoryComponentAction.CategoryCreated -> {
                            reduceState { copy(selectedCategory = action.category) }
                            dialogNavigation.dismiss()
                            getCategories()
                        }
                    }
                }
            )
        )

    private fun onAddNewCategoryClick() {
        reduceState { copy(categoriesMenuIsExpanded = false) }
        dialogNavigation.activate(DialogConfig.CreateCategory)
    }

    private fun onAmountChange(amount: String) {
        reduceState {
            if (amount.lastOrNull()?.isDigit() == true || amount.isEmpty()) {
                copy(maxAmount = amount, maxAmountError = null)
            } else {
                copy(maxAmountError = "Поле принимает только цифры")
            }
        }
    }

    private fun onCancelClick() {
        onAction(NewBudgetComponentAction.Canceled)
    }

    private fun onCategorySelect(category: Category) {
        reduceState {
            copy(
                selectedCategory = category,
                categoriesMenuIsExpanded = false,
                categorySearch = category.name
            )
        }
    }

    private fun onManageCategoryDropdown() {
        reduceState { copy(categoriesMenuIsExpanded = !categoriesMenuIsExpanded) }
    }

    private fun onRegularClick() {
        reduceState { copy(isRegular = !isRegular) }
    }

    private fun onUpdateCategorySearch(search: String) {
        reduceState { copy(categorySearch = search) }
    }

    private fun getCategories() {
        coroutineScope.launch {
            val result = dependencies.getCategoriesUseCase.execute(Unit)

            GetCategoriesResultHandler(
                result = result,
                delegate = this@NewBudgetComponentImpl
            ).handle()
        }
    }

    private fun onCreateBudgetCategoryClick() {
        coroutineScope.launch {
            val result =
                dependencies.addBudgetCategoryUseCase.execute(
                    params = AddBudgetCategoryUseCase.Params(
                        categoryId = state.value.selectedCategory.id,
                        maxAmount = state.value.maxAmount.toFloat(),
                        isRegular = state.value.isRegular,
                        nextTransactionDate = if (state.value.isRegular) {
                            LocalDate.now().atStartOfMonth().plus(1, DateTimeUnit.MONTH)
                        } else {
                            null
                        }
                    )
                )

            AddBudgetCategoryResultHandler(
                result = result,
                onAction = { onAction(NewBudgetComponentAction.BudgetCreated) },
            ).handle()
        }
    }

    @Serializable
    private sealed interface DialogConfig {
        data object CreateCategory : DialogConfig
    }
}
