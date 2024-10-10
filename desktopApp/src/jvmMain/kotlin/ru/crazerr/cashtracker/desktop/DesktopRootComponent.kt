package ru.crazerr.cashtracker.desktop

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.crazerr.cashtracker.core.mediator.RootComponent

class DesktopRootComponent : KoinComponent {
    private val rootComponentFactory: RootComponent.Factory by inject()

    fun create(lifecycleRegistry: LifecycleRegistry): RootComponent =
        rootComponentFactory.create(DefaultComponentContext(lifecycleRegistry))
}
