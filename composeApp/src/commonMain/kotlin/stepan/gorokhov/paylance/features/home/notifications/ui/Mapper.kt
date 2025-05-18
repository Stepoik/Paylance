package stepan.gorokhov.paylance.features.home.notifications.ui

import stepan.gorokhov.paylance.core.time.formatDateHoursMinutes
import stepan.gorokhov.paylance.core.time.fromUTC
import stepan.gorokhov.paylance.features.home.notifications.domain.models.ProjectNotification

fun ProjectNotification.toVO() = NotificationVO(
    id = id,
    type = type,
    title = title,
    message = message,
    time = time.fromUTC().formatDateHoursMinutes(),
    projectId = projectId,
    responseId = responseId
)