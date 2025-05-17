package stepan.gorokhov.paylance.features.home.notifications.domain

import stepan.gorokhov.paylance.features.home.notifications.domain.models.ProjectNotification

interface NotificationsRepository {
    suspend fun getNotifications(offset: Long): Result<List<ProjectNotification>>
    suspend fun markAsRead(notificationId: String)
    suspend fun deleteNotification(notificationId: String)
} 