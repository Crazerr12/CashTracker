package ru.crazerr.cashtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.android.inject
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.mediator.RootComponent
import ru.crazerr.cashtracker.core.mediator.RootCoordinator

class AppActivity : ComponentActivity() {
    private val rootComponentFactory: RootComponent.Factory by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val rootComponent = rootComponentFactory.create(
            componentContext = defaultComponentContext()
        )

        setContent {
            AppTheme {
                RootCoordinator(rootComponent = rootComponent)
            }
        }
    }
}
