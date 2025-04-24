package stepan.gorokhov.paylance.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.features.home.profile.ui.profile
import stepan.gorokhov.paylance.features.home.tasks.tasks

fun NavGraphBuilder.home(parentNavController: NavController) {
    composable(ApplicationRoute.Home.route) {
        val navController = rememberNavController()

        NavHost(navController, startDestination = HomeRoute.Tasks.route) {
            profile(navController)
            tasks(navController)
        }
    }
}