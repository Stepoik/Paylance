package stepan.gorokhov.paylance.features.auth.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.features.auth.ui.signin.signIn
import stepan.gorokhov.paylance.features.auth.ui.signup.signUp

fun NavGraphBuilder.auth(navController: NavController) {
    navigation(route = ApplicationRoute.Auth.route, startDestination = AuthRoute.SignUp.route) {
        signIn(navController)
        signUp(navController)
    }
}