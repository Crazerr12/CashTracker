package ru.crazerr.cashtracker.feature.category.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory.AddCategoryUseCase
import ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory.AddCategoryUseCaseImpl

val categoryDomainModule = module {
    factory<AddCategoryUseCase> {
        AddCategoryUseCaseImpl(
            categoryRepository = get()
        )
    }
}