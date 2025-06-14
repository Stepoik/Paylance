package stepan.gorokhov.paylance.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.paylance.coreui.routing.popUpGraph
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.features.home.chats.chat
import stepan.gorokhov.paylance.features.home.notifications.notifications
import stepan.gorokhov.paylance.features.home.profile.ui.profile
import stepan.gorokhov.paylance.features.home.projects.projects
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.myProjects

fun NavGraphBuilder.home(parentNavController: NavController) {
    composable<ApplicationRoute.Home> {
        val viewModel = koinViewModel<HomeViewModel>()
        LaunchedEffect(Unit) {
            viewModel.isAuthorized.collect {
                if (!it) {
                    parentNavController.navigate(ApplicationRoute.Auth) { popUpGraph() }
                }
            }
        }
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                NavigationBar(containerColor = PaylanceTheme.colors.surface) {
                    val items = listOf(
                        HomeRoute.Projects,
                        HomeRoute.MyProjects,
                        HomeRoute.Chats,
                        HomeRoute.Notifications,
                        HomeRoute.Profile,
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
                                unselectedIconColor = PaylanceTheme.colors.border,
                                indicatorColor = PaylanceTheme.colors.primary.copy(alpha = 0.2f)
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
        ) { scaffoldPadding ->

            val navigationBarBottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            val scaffoldBottomPadding = scaffoldPadding.calculateBottomPadding()
            val bottomPadding = scaffoldBottomPadding - navigationBarBottomPadding
            NavHost(
                navController,
                startDestination = HomeRoute.Projects,
                modifier = Modifier.padding(bottom = bottomPadding)
            ) {
                profile(navController)
                projects(navController)
                myProjects(navController)
                notifications(navController)
                chat(navController)
            }
        }
    }
}