import androidx.compose.ui.window.ComposeUIViewController
import ru.vlyashuk.mapnotes.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
