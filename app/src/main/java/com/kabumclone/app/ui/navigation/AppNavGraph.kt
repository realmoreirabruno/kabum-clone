package com.kabumclone.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kabumclone.app.ui.screens.AccountScreen
import com.kabumclone.app.ui.screens.departments.DepartamentsScreen
import com.kabumclone.app.ui.screens.NotificationsScreen
import com.kabumclone.app.ui.screens.HomeScreen
import com.kabumclone.app.ui.screens.cart.CartScreen
import com.kabumclone.app.ui.screens.product.ProductDetailsScreen

@Composable
fun AppNavGraph(navController: NavHostController, onGoogleLoginClicked: () -> Unit) {
    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) { HomeScreen(navController) }
        composable(NavRoutes.Departaments.route) { DepartamentsScreen(navController) }
        composable(NavRoutes.Notifications.route) { NotificationsScreen() }
        composable("carrinho") { CartScreen() }
        composable(NavRoutes.Conta.route) {
            AccountScreen(onGoogleLoginClicked = onGoogleLoginClicked)
        }
        composable("produto/{produtoId}") { backStackEntry ->
            val produtoId = backStackEntry.arguments?.getString("produtoId") ?: ""
            ProductDetailsScreen(navController = navController, produtoId = produtoId)
        }
    }
}
