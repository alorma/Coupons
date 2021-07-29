package com.alorma.cupons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.cupons.ui.components.AppBar
import com.alorma.cupons.ui.theme.CuponsTheme
import com.google.accompanist.insets.ui.Scaffold
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuponsTheme {
                Scaffold(
                    topBar = {
                        AppBar(title = { Text(text = "Cupons") })
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { contentPadding ->
                    Box(Modifier.padding(contentPadding)) {
                        Greeting(Random.nextInt(1_000).toString())
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CuponsTheme {
        Greeting("Android")
    }
}