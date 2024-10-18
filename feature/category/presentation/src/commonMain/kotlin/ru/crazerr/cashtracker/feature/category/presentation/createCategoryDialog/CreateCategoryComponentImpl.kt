package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import ru.crazerr.cashtracker.core.compose.icons.AppIcon
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

    override fun obtainViewAction(action: CreateCategoryViewAction) {
        when (action) {
            is CreateCategoryViewAction.UpdateName -> onUpdateName(name = action.name)
            CreateCategoryViewAction.CancelClick -> onCancelClick()
            CreateCategoryViewAction.SaveClick -> onCreateNewCategoryClick()
            is CreateCategoryViewAction.UpdateColor -> onUpdateColor(color = action.color)
            CreateCategoryViewAction.ManageIconsDropdownMenu -> onManageIconsDropdownMenu()
            is CreateCategoryViewAction.UpdateIcon -> onUpdateIcon(icon = action.icon)
        }
    }

    private fun onUpdateIcon(icon: AppIcon) {
        reduceState { copy(selectedIcon = icon, iconsDropdownIsExpanded = false) }
    }

    private fun onManageIconsDropdownMenu() {
        reduceState { copy(iconsDropdownIsExpanded = !iconsDropdownIsExpanded) }
    }

    private fun onUpdateColor(color: Color) {
        reduceState { copy(selectedColor = color) }
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
                dependencies.addCategoryUseCase.execute(
                    params = AddCategoryUseCase.Params(
                        name = state.value.name,
                        color = state.value.selectedColor.toArgb(),
                        iconId = state.value.selectedIcon.id
                    )
                )

            AddCategoryResultHandler(
                result = result,
                onAction = {
                    onAction(
                        CreateCategoryComponentAction.CategoryCreated(
                            category = Category(
                                id = it,
                                name = state.value.name,
                                iconId = state.value.selectedIcon.id,
                                color = state.value.selectedColor.toArgb()
                            )
                        )
                    )
                },
            ).handle()
        }
    }
}
