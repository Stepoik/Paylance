package stepan.gorokhov.paylance.features.home.projects.ui.myProjects

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.coreui.routing.BaseRoute
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.projects.ui.createProject.CreateProjectScreen
import stepan.gorokhov.paylance.features.home.projects.ui.detail.ProjectDetailsScreen
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.screens.MyProjectsScreen

@Serializable
sealed class MyProjectsRoute {
    @Serializable
    data object Main : MyProjectsRoute()
    @Serializable
    data class Details(val id: String) : MyProjectsRoute()
    @Serializable
    data object CreateProject : MyProjectsRoute()
}

fun NavGraphBuilder.myProjects(navController: NavController) {
    navigation<HomeRoute.MyProjects>(startDestination = MyProjectsRoute.Main) {
        composable<MyProjectsRoute.Main> {
            MyProjectsScreen(navController)
        }

        composable<MyProjectsRoute.CreateProject> {
            CreateProjectScreen(navController)
        }

        composable<MyProjectsRoute.Details> { backStackEntry ->
            val id = backStackEntry.toRoute<MyProjectsRoute.Details>().id
            ProjectDetailsScreen(navController, projectId = id)
        }
    }
}