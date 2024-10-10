package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

import ru.crazerr.cashtracker.core.utils.presentation.StateHolder

abstract class CreateCategoryComponent :
    StateHolder<CreateCategoryState, CreateCategoryViewAction>(InitialCreateCategoryState)
