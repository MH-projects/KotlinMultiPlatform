import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen()) { navigator ->
//            SlideTransition(navigator)
//            FadeTransition(navigator)
            ScaleTransition(navigator)
        }
    }
}

class MainScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current

        var name: String by remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = name,
                onValueChange = { name = it }
            )
            Spacer(modifier = Modifier.height(38.dp))
            AnimatedVisibility(name.isNotEmpty()) {
                Text("Hola $name", fontSize = 24.sp)
            }
            Button(onClick = {
                navigator?.push(SecondScreen())
            }) {
                Text("Navigator")
            }
        }
    }
}

class SecondScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Segunda pantalla", fontSize = 26.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navigator.pop()
            }) {
                Text("Back", color = Color.White)
            }
        }
    }
}