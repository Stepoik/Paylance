package stepan.gorokhov.paylance.features

import kotlinx.serialization.Serializable

@Serializable
sealed class ApplicationRoute {
    @Serializable
    data object Splash : ApplicationRoute()
    @Serializable
    data object Auth : ApplicationRoute()
    @Serializable
    data object Home : ApplicationRoute()
}