package stepan.gorokhov.paylance.features.home

import stepan.gorokhov.paylance.coreui.routing.BaseRoute
import stepan.gorokhov.paylance.features.ApplicationRoute

private val BASE_ROUTE = ApplicationRoute.Home.route

sealed class HomeRoute(override val route: String) : BaseRoute {
    data object Profile : HomeRoute("$BASE_ROUTE/profile")
    data object Tasks : HomeRoute("$BASE_ROUTE/tasks")
}