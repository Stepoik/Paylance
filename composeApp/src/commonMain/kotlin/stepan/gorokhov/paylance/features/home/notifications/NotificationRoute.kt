package stepan.gorokhov.paylance.features.home.notifications

import kotlinx.serialization.Serializable

@Serializable
sealed class NotificationRoute {
    @Serializable
    data object Notifications : NotificationRoute()

    @Serializable
    data class ProjectDetails(val id: String) : NotificationRoute()

    @Serializable
    data class ResponseDetails(val id: String) : NotificationRoute()
}