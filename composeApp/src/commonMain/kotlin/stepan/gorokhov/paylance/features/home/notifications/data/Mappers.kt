package stepan.gorokhov.paylance.features.home.notifications.data

import stepan.gorokhov.paylance.features.home.notifications.data.network.models.NotificationDto
import stepan.gorokhov.paylance.features.home.notifications.data.network.models.NotificationTypeDto
import stepan.gorokhov.paylance.features.home.notifications.domain.models.NotificationType
import stepan.gorokhov.paylance.features.home.notifications.domain.models.ProjectNotification

fun NotificationDto.toDomain() = ProjectNotification(
    id = id,
    type = type.toDomain(),
    title = title,
    message = message,
    time = createdAt,
    projectId = projectId,
    responseId = responseId
)

fun NotificationTypeDto.toDomain(): NotificationType {
    return when (this) {
        NotificationTypeDto.PROJECT_RESPONSE -> NotificationType.PROJECT_RESPONSE
        NotificationTypeDto.RESPONSE_RESULT -> NotificationType.RESPONSE_RESULT
    }
}