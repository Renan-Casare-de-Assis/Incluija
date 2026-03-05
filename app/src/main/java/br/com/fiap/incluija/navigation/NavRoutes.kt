package br.com.fiap.incluija.navigation

/**
 * Rotas de navegação do app
 */
sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Cadastro : NavRoutes("cadastro")
    object Home : NavRoutes("home")
    object Perfil : NavRoutes("perfil")
    object Candidaturas : NavRoutes("candidaturas")
}
