package ru.crazerr.cashtracker.feature.category.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.category.data.categoryDataModule
import ru.crazerr.cashtracker.feature.category.domain.categoryDomainModule
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewFactory
import ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.CreateCategoryComponentFactoryImpl
import ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.CreateCategoryViewFactoryImpl

val categoryPresentationModule = module {
    factory<CreateCategoryViewFactory> {
        CreateCategoryViewFactoryImpl()
    }

    factory<CreateCategoryComponentFactory> {
        CreateCategoryComponentFactoryImpl(
            addCategoryUseCase = get()
        )
    }

    includes(categoryDomainModule, categoryDataModule)
}
