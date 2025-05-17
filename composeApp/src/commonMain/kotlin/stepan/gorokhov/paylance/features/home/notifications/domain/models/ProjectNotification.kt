package stepan.gorokhov.paylance.features.home.notifications.domain.models

import kotlinx.datetime.LocalDateTime


data class ProjectNotification(
    val id: String,
    val type: NotificationType,
    val title: String,
    val message: String,
    val time: LocalDateTime,
    val projectId: String,
    val responseId: String,
)

enum class NotificationType {
    PROJECT_RESPONSE, // Отклик на проект
    RESPONSE_RESULT // Результат отклика
}
