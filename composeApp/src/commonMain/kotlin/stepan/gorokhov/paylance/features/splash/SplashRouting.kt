package stepan.gorokhov.paylance.features.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.paylance.features.ApplicationRoute

fun NavGraphBuilder.splash(navController: NavController) {
    composable(ApplicationRoute.Splash.route) {
        SplashScreen(navController)
    }
}