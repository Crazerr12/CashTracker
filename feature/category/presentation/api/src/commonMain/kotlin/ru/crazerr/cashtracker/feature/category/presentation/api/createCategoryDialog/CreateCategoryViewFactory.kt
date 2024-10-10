package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface CreateCategoryViewFactory {
    @Composable
    fun create(modifier: Modifier, component: CreateCategoryComponent)
}