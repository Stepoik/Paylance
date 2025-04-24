package stepan.gorokhov.paylance.features.home.tasks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.tasks.ui.TasksRoute

fun NavGraphBuilder.tasks(navController: NavController) {
    navigation(route = HomeRoute.Tasks.route, startDestination = TasksRoute.Main.route) {
        composable(TasksRoute.Main.route) {

        }

        composable(TasksRoute.Details.route) {

        }
    }
}