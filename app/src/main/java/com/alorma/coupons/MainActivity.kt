package com.alorma.coupons

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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.coupons.ui.components.AppBar
import com.alorma.coupons.ui.debugmodules.ConfigureScreen
import com.alorma.coupons.ui.screen.list.CouponsListScreen
import com.alorma.coupons.ui.theme.CouponsTheme
import com.google.accompanist.insets.ui.Scaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CouponsTheme() {
                Scaffold(
                    topBar = {
                        AppBar(title = { Text(text = "Coupons") })
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        val navController = rememberNavController()
                        ConfigureScreen {
                            NavHost(
                                navController = navController,
                                startDestination = "list",
                            ) {
                                composable(route = "list") { CouponsListScreen() }
                            }
                        }
                    }
                }
            }
        }
    }
}