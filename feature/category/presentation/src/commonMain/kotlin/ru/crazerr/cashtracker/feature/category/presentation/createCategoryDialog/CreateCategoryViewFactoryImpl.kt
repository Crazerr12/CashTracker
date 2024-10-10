package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponent
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewFactory
import ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.ui.CreateCategoryView

internal class CreateCategoryViewFactoryImpl : CreateCategoryViewFactory {
    @Composable
    override fun create(modifier: Modifier, component: CreateCategoryComponent) {
        CreateCategoryView(modifier = modifier, component = component)
    }
}
