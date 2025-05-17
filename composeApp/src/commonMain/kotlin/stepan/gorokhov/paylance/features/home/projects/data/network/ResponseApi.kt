package stepan.gorokhov.paylance.features.home.projects.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ReplyOnResponseRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ResponseDto
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ResponseOnProjectRequest
import stepan.gorokhov.paylance.network.Constants

private const val BASE_URL = "${Constants.BASE_URL}/responses"

class ResponseApi(
    private val httpClient: HttpClient
) {
    suspend fun responseOnProject(request: ResponseOnProjectRequest) {
        httpClient.post(BASE_URL) {
            setBody(request)
        }
    }

    suspend fun getResponseById(id: String): ResponseDto {
        return httpClient.get("$BASE_URL/$id").body()
    }

    suspend fun replyOnResponse(request: ReplyOnResponseRequest) {
        httpClient.post("$BASE_URL/reply") {
            setBody(request)
        }
    }
}