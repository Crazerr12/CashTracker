package ru.crazerr.cashtracker.feature.main.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.main.data.mainDataModule
import ru.crazerr.cashtracker.feature.main.domain.mainDomainModule
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponentFactory
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponentFactoryImpl
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.MainStoryComponentFactory
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.MainStoryComponentFactoryImpl

val mainPresentationModule = module {
    single<MainStoryComponentFactory> {
        MainStoryComponentFactoryImpl(get())
    }
    single<MainComponentFactory> {
        MainComponentFactoryImpl(get(), get())
    }
    includes(mainDomainModule, mainDataModule)
}