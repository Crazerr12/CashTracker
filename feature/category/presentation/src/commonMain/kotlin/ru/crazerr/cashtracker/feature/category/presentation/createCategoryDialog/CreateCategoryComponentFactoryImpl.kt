package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory.AddCategoryUseCase
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentAction
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory

internal class CreateCategoryComponentFactoryImpl(
    private val addCategoryUseCase: AddCategoryUseCase,
) : CreateCategoryComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (CreateCategoryComponentAction) -> Unit,
    ) = CreateCategoryComponentImpl(
        componentContext = componentContext,
        onAction = onAction,
        dependencies = CreateCategoryDependencies(
            addCategoryUseCase = addCategoryUseCase
        )
    )
}