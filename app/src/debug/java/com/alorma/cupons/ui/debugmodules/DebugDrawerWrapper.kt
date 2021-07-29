package com.alorma.cupons.ui.debugmodules

import androidx.compose.runtime.Composable
import com.alorma.drawer_base.DebugDrawerLayout

@Composable
fun ConfigureScreen(content: @Composable (isDrawerOpen: Boolean) -> Unit) {
    DebugDrawerLayout(
        drawerModules = { DebugModules() }
    ) {
        content(it.isOpen)
    }
}
