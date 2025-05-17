package stepan.gorokhov.paylance.features.home.notifications.ui

import stepan.gorokhov.paylance.features.home.notifications.domain.models.ProjectNotification

fun ProjectNotification.toVO() = NotificationVO(
    id = id,
    type = type,
    title = title,
    message = message,
    time = time.toString(),
    projectId = projectId,
    responseId = responseId
)