package ru.crazerr.cashtracker.core.mediator

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.crazerr.cashtracker.core.utils.utilsModule
import ru.crazerr.cashtracker.feature.main.presentation.mainPresentationModule
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.MainStoryComponentFactory

val mediatorModule = module {
    single<RootComponent.Factory> {
        RootComponentImpl.FactoryImpl(
        )
    }
}

internal val internalModule = module {
    includes(mainPresentationModule)
}

internal class InternalComponent : KoinComponent {
    val mainStoryComponentFactory: MainStoryComponentFactory by inject()

    companion object {
        fun create(): InternalComponent {
            loadKoinModules(internalModule)
            return InternalComponent()
        }
    }
}