package stepan.gorokhov.paylance.features.home.notifications.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GetNotificationsResponse(
    val notifications: List<NotificationDto>,
    val total: Long
)