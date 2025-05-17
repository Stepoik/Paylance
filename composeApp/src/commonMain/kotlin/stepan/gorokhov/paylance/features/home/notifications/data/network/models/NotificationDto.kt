package stepan.gorokhov.paylance.features.home.notifications.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    val id: String,
    val userId: String,
    val projectId: String,
    val title: String,
    val message: String,
    val createdAt: LocalDateTime,
    val type: NotificationTypeDto,
    val isRead: Boolean,
    val responseId: String
)

enum class NotificationTypeDto {
    PROJECT_RESPONSE, // Отклик на проект
    RESPONSE_RESULT // Результат отклика
}
