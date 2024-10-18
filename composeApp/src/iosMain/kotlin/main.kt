import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ru.crazerr.cashtracker.App

@Suppress("FunctionNaming")
fun MainViewController(): UIViewController = ComposeUIViewController { App() }
