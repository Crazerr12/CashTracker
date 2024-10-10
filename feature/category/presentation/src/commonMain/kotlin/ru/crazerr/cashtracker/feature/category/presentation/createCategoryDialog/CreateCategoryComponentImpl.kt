package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import ru.crazerr.cashtracker.core.utils.coroutine.componentCoroutineScope
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory.AddCategoryUseCase
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponent
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentAction
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewAction
import ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.handler.AddCategoryResultHandler

internal class CreateCategoryComponentImpl(
    componentContext: ComponentContext,
    private val onAction: (CreateCategoryComponentAction) -> Unit,
    private val dependencies: CreateCategoryDependencies,
) : CreateCategoryComponent(), ComponentContext by componentContext {

    private val coroutineScope = componentCoroutineScope()

    init {
        // TODO(Сделать загрузку иконок и цвета)
    }

    override fun obtainViewAction(action: CreateCategoryViewAction) {
        when (action) {
            is CreateCategoryViewAction.UpdateName -> onUpdateName(name = action.name)
            CreateCategoryViewAction.CancelClick -> onCancelClick()
            CreateCategoryViewAction.SaveClick -> onCreateNewCategoryClick()
        }
    }

    private fun onUpdateName(name: String) {
        reduceState { copy(name = name) }
    }

    private fun onCancelClick() {
        onAction(CreateCategoryComponentAction.Canceled)
    }

    private fun onCreateNewCategoryClick() {
        reduceState { copy(buttonLoading = true) }
        coroutineScope.launch {
            val result =
                dependencies.addCategoryUseCase.execute(params = AddCategoryUseCase.Params(name = state.value.name))

            AddCategoryResultHandler(
                result = result,
                onAction = {
                    onAction(
                        CreateCategoryComponentAction.CategoryCreated(
                            category = Category(
                                id = it,
                                name = state.value.name
                            )
                        )
                    )
                },
            ).handle()
        }
    }
}