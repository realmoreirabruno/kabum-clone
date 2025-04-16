package com.kabumclone.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.kabumclone.app.ui.navigation.NavRoutes

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, NavRoutes.Home.route),
        BottomNavItem("Departaments", Icons.AutoMirrored.Filled.List, NavRoutes.Departaments.route),
        BottomNavItem("Favorites", Icons.Default.Favorite, NavRoutes.Favorites.route),
        BottomNavItem("Notifications", Icons.Default.Notifications, NavRoutes.Notifications.route),
        BottomNavItem("Minha Conta", Icons.Default.Person, NavRoutes.Conta.route)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}