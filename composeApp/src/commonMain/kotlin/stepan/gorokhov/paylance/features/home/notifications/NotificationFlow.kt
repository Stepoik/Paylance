package stepan.gorokhov.paylance.features.home.notifications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.notifications.ui.NotificationsScreen
import stepan.gorokhov.paylance.features.home.projects.ui.detail.ProjectDetailsScreen
import stepan.gorokhov.paylance.features.home.projects.ui.response.ProjectResponseScreen

fun NavGraphBuilder.notifications(navController: NavController) {
    navigation<HomeRoute.Notifications>(startDestination = NotificationRoute.Notifications) {
        composable<NotificationRoute.Notifications> {
            NotificationsScreen(navController)
        }
        composable<NotificationRoute.ProjectDetails> {
            val id = it.toRoute<NotificationRoute.ProjectDetails>().id
            ProjectDetailsScreen(navController, projectId = id)
        }
        composable<NotificationRoute.ResponseDetails> {
            val id = it.toRoute<NotificationRoute.ResponseDetails>().id
            ProjectResponseScreen(navController, responseId = id)
        }
    }
}