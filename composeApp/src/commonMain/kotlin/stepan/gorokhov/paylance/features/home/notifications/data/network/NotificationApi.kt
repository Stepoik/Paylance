package stepan.gorokhov.paylance.features.home.notifications.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import stepan.gorokhov.paylance.features.home.notifications.data.network.models.GetNotificationsResponse
import stepan.gorokhov.paylance.network.Constants

private const val BASE_URL = "${Constants.BASE_URL}/notifications"

class NotificationApi(
    private val httpClient: HttpClient
) {
    suspend fun getNotifications(offset: Long): GetNotificationsResponse {
        return httpClient.get(BASE_URL) {
            parameter("offset", offset)
        }.body()
    }
}