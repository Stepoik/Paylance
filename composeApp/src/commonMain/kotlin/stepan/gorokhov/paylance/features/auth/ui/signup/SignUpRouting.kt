package stepan.gorokhov.paylance.features.auth.ui.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.paylance.features.auth.ui.AuthRoute

internal fun NavGraphBuilder.signUp(navController: NavController) {
    composable<AuthRoute.SignUp> {
        SignUpScreen(navController)
    }
}