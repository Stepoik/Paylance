package stepan.gorokhov.paylance.features.home.tasks.ui

import stepan.gorokhov.paylance.coreui.routing.BaseRoute
import stepan.gorokhov.paylance.features.home.HomeRoute

private val BASE_ROUTE = HomeRoute.Tasks.route

sealed class TasksRoute(override val route: String) : BaseRoute {
    data object Main : TasksRoute("$BASE_ROUTE/main")
    data object Details : TasksRoute("$BASE_ROUTE/details")
}