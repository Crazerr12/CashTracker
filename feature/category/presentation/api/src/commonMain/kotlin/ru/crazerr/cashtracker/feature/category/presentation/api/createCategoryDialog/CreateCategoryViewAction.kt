package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

import androidx.compose.ui.graphics.Color
import ru.crazerr.cashtracker.core.compose.icons.AppIcon

sealed interface CreateCategoryViewAction {
    data object CancelClick : CreateCategoryViewAction
    data class UpdateName(val name: String) : CreateCategoryViewAction
    data class UpdateColor(val color: Color) : CreateCategoryViewAction
    data class UpdateIcon(val icon: AppIcon) : CreateCategoryViewAction
    data object SaveClick : CreateCategoryViewAction
    data object ManageIconsDropdownMenu : CreateCategoryViewAction
}
