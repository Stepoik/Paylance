package stepan.gorokhov.paylance

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.features.auth.ui.auth
import stepan.gorokhov.paylance.features.home.home
import stepan.gorokhov.paylance.features.splash.splash

@Composable
@Preview
fun App() {
    PaylanceTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = ApplicationRoute.Splash) {
            splash(navController)
            auth(navController)
            home(navController)

        }
    }
}