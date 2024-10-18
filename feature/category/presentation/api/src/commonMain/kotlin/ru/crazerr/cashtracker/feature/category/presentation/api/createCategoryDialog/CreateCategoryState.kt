package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

import androidx.compose.ui.graphics.Color
import ru.crazerr.cashtracker.core.compose.icons.AppIcon
import ru.crazerr.cashtracker.core.compose.icons.AppIcons

data class CreateCategoryState(
    val name: String,
    val buttonLoading: Boolean,
    val selectedColor: Color,
    val selectedIcon: AppIcon,
    val iconsDropdownIsExpanded: Boolean,
)

internal val InitialCreateCategoryState = CreateCategoryState(
    name = "",
    buttonLoading = false,
    selectedColor = Color.Unspecified,
    selectedIcon = AppIcons.Main,
    iconsDropdownIsExpanded = false,
)
