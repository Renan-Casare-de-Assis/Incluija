package br.com.fiap.incluija.navigation

/**
 * Rotas de navegação do app
 */
sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Cadastro : NavRoutes("cadastro")
}

