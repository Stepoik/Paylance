package stepan.gorokhov.paylance.features.home.projects

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.projects.ui.ProjectsRoute
import stepan.gorokhov.paylance.features.home.projects.ui.detail.ProjectDetailsScreen
import stepan.gorokhov.paylance.features.home.projects.ui.main.ProjectsMainScreen

fun NavGraphBuilder.projects(navController: NavController) {
    navigation<HomeRoute.Projects>(startDestination = ProjectsRoute.Main) {
        composable<ProjectsRoute.Main> {
            ProjectsMainScreen(navController)
        }

        composable<ProjectsRoute.Details> { backStackEntry ->
            val id = backStackEntry.toRoute<ProjectsRoute.Details>().id
            ProjectDetailsScreen(navController, projectId = id)
        }
    }
}