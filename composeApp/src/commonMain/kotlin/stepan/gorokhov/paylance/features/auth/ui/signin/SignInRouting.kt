package stepan.gorokhov.paylance.features.auth.ui.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.paylance.features.auth.ui.AuthRoute

internal fun NavGraphBuilder.signIn(navController: NavController) {
    composable(AuthRoute.SignIn.route) {
        SignInScreen(navController)
    }
}