package stepan.gorokhov.paylance.features.home.notifications.data

import stepan.gorokhov.paylance.features.home.notifications.data.network.NotificationApi
import stepan.gorokhov.paylance.features.home.notifications.domain.NotificationsRepository
import stepan.gorokhov.paylance.features.home.notifications.domain.models.ProjectNotification

class NotificationRepositoryImpl(
    private val notificationApi: NotificationApi
) : NotificationsRepository {
    override suspend fun getNotifications(offset: Long): Result<List<ProjectNotification>> {
        return runCatching {
            notificationApi.getNotifications(offset).notifications.map { it.toDomain() }
        }
    }

    override suspend fun markAsRead(notificationId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNotification(notificationId: String) {
        TODO("Not yet implemented")
    }
}