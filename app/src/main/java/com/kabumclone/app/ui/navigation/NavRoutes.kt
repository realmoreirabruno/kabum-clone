package com.kabumclone.app.ui.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Departaments : NavRoutes("departaments")
    object Favorites : NavRoutes("favorites")
    object Notifications : NavRoutes("notifications")
    object Conta : NavRoutes("conta")
}