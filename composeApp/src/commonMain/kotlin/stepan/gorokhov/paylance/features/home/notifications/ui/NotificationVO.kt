package stepan.gorokhov.paylance.features.home.notifications.ui

import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.features.home.notifications.domain.models.NotificationType

@Serializable
data class NotificationVO(
    val id: String,
    val type: NotificationType,
    val title: String,
    val message: String,
    val time: String,
    val projectId: String,
    val responseId: String,
)