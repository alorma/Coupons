package com.alorma.cupons.ui.debugmodules

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alorma.drawer_modules.BuildModule
import com.alorma.drawer_modules.DeviceModule

object DebugModules {

    @SuppressLint("ComposableNaming")
    @Composable
    operator fun invoke() {
        val modulesModifier = Modifier
            .padding(4.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colors.surface)

        DeviceModule(modifier = modulesModifier)
        BuildModule(modifier = modulesModifier)
    }
}