package stepan.gorokhov.paylance.features.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.features.home.notifications.notifications
import stepan.gorokhov.paylance.features.home.profile.ui.profile
import stepan.gorokhov.paylance.features.home.projects.projects
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.myProjects

fun NavGraphBuilder.home(parentNavController: NavController) {
    composable<ApplicationRoute.Home> {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                NavigationBar(containerColor = PaylanceTheme.colors.background) {
                    val items = listOf(
                        HomeRoute.Projects,
                        HomeRoute.MyProjects,
                        HomeRoute.Profile,
                        HomeRoute.Notifications
                    )

                    items.forEach { screen ->
                        val currentDestination =
                            navController.currentBackStackEntryAsState().value?.destination
                        val selected =
                            currentDestination?.hierarchy?.any { it.route == screen::class.qualifiedName } == true
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = PaylanceTheme.colors.primary,
                                unselectedIconColor = PaylanceTheme.colors.border
                            ),
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(screen) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(navController, startDestination = HomeRoute.Projects) {
                profile(navController)
                projects(navController)
                myProjects(navController)
                notifications(navController)
            }
        }
    }
}