package com.aimgstudio.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
fun AimgBottomNav(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "gallery",
            onClick = { onNavigate("gallery") },
            label = { Text("Gallery") },
            icon = { /* Icon here */ }
        )
        NavigationBarItem(
            selected = currentRoute == "collections",
            onClick = { onNavigate("collections") },
            label = { Text("Collections") },
            icon = { /* Icon here */ }
        )
        NavigationBarItem(
            selected = currentRoute == "ai",
            onClick = { onNavigate("ai") },
            label = { Text("AI") },
            icon = { /* Icon here */ }
        )
        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = { onNavigate("settings") },
            label = { Text("Settings") },
            icon = { /* Icon here */ }
        )
    }
}
