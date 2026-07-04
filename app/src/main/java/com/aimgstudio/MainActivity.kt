package com.aimgstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aimgstudio.ui.navigation.AimgNavGraph
import com.aimgstudio.ui.components.AimgBottomNav
import com.aimgstudio.ui.theme.AimgTheme
import androidx.compose.material3.ColorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var seedColor by remember { mutableStateOf("#6750A4") }
            var isDark by remember { mutableStateOf(false) }
            
            val colorScheme = AimgTheme.getDynamicColorScheme(seedColor, isDark)
            
            MaterialTheme(colorScheme = colorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    Scaffold(
                        bottomBar = {
                            AimgBottomNav(
                                currentRoute = currentRoute ?: "gallery",
                                onNavigate = { route ->
                                    navController.navigate(route)
                                }
                            )
                        }
                    ) { innerPadding ->
                        androidx.compose.foundation.layout.Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            AimgNavGraph(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavHostController.currentBackStackEntryAsState(): androidx.compose.runtime.State<androidx.navigation.NavBackStackEntry?> {
    return androidx.navigation.compose.rememberCurrentBackStackEntryAsState()
}
