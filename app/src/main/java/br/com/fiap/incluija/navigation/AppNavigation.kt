package br.com.fiap.incluija.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.incluija.TelaLogin
import br.com.fiap.incluija.TelaCadastro

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
                }
            )
        }

        composable(route = NavRoutes.Cadastro.route) {
            TelaCadastro(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

