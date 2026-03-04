package br.com.fiap.incluija.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.incluija.TelaLogin
import br.com.fiap.incluija.TelaCadastro
import br.com.fiap.incluija.TelaHome
import br.com.fiap.incluija.TelaPerfil

/**
 * Configura a navegação principal do app
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Login.route
    ) {
        composable(route = NavRoutes.Login.route) {
            TelaLogin(
                onNavigateToCadastro = {
                    navController.navigate(NavRoutes.Cadastro.route)
                },
                onNavigateToHome = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = NavRoutes.Cadastro.route) {
            TelaCadastro(
                onNavigateBack = {
                    navController.navigate(NavRoutes.Login.route) {
                        popUpTo(NavRoutes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = NavRoutes.Home.route) {
            TelaHome(
                onNavigation = { route ->
                    navController.navigate(route) {
                        popUpTo(NavRoutes.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(route = NavRoutes.Perfil.route) {
            TelaPerfil()
        }
    }
}
