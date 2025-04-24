package stepan.gorokhov.paylance.features.auth.ui

import stepan.gorokhov.paylance.coreui.routing.BaseRoute
import stepan.gorokhov.paylance.features.ApplicationRoute

private val BASE_ROUTE = ApplicationRoute.Auth.route

sealed class AuthRoute(override val route: String) : BaseRoute {
    data object SignIn : AuthRoute("$BASE_ROUTE/sign_in")
    data object SignUp : AuthRoute("$BASE_ROUTE/sign_up")
}