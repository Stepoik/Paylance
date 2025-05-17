package stepan.gorokhov.paylance.features.auth.ui

import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.coreui.routing.BaseRoute
import stepan.gorokhov.paylance.features.ApplicationRoute

@Serializable
sealed class AuthRoute {
    @Serializable
    data object SignIn : AuthRoute()
    @Serializable
    data object SignUp : AuthRoute()
}