package com.aimgstudio.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aimgstudio.ui.screens.*

sealed class Screen(val route: String) {
    object Gallery : Screen("gallery")
    object Collections : Screen("collections")
    object AI : Screen("ai")
    object Settings : Screen("settings")
}

@Composable
fun AimgNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Gallery.route) {
        composable(Screen.Gallery.route) {
            GalleryScreen(navController)
        }
        composable(Screen.Collections.route) {
            CollectionScreen(navController)
        }
        composable(Screen.AI.route) {
            AIScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
    }
}
