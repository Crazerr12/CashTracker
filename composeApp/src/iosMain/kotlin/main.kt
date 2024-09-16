import androidx.compose.ui.window.ComposeUIViewController
import ru.crazerr.cashtracker.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
