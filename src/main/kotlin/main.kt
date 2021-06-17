import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.v1.MenuItem
import androidx.compose.ui.window.v1.Tray
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

val windowIcon = getIcon()
fun main() = Window(icon = windowIcon) {
    val list = listOf(
        "Java",
        "Python",
        "Groovy",
        "Rust",
        "Kotlin",
        "Dart",
        "C#",
        "F#",
        "Ruby",
    )
    var text by remember { mutableStateOf("Hello, World!") }

    DisposableEffect(Unit) {
        val tray = Tray().apply {
            icon(getIcon())
            menu(
                MenuItem(name = "退出应用", onClick = { AppManager.exit() })
            )
        }
        onDispose {
            tray.remove()
        }
    }
    MaterialTheme {
        Scaffold(
            topBar = {

            }
        ) {
            LazyColumn {
                items(count = list.size) {
                    Card(modifier = Modifier.offset(y = 10.dp)) {
                        Row (modifier = Modifier.padding(10.dp).fillMaxWidth()){
                            Image(
                                bitmap = imageResource("images/sample.png"),
                                contentDescription = "Sample",
                                modifier = Modifier.width(64.dp).height(64.dp)
                            )
                            Column (modifier = Modifier.offset(x = 10.dp)) {
                                Text("hello")

                                Button(onClick = {
                                    text = "Hello, Desktop2!"
                                }) {
                                    Text(text)
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

fun getIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("main/images/sample.png"))
    } catch (e: Exception) {
        // ignore
    }
    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }
    return image
}