package stepan.gorokhov.paylance.features.home.tasks.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.CreateTaskRequest
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.GetTasksResponse
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.TaskDto
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.UpdateTaskRequest
import stepan.gorokhov.paylance.network.Constants

private const val BASE_URL = "${Constants.BASE_URL}/orders"

class TasksApi(
    private val httpClient: HttpClient
) {
    suspend fun createTask(createTaskRequest: CreateTaskRequest): TaskDto {
        return httpClient.post(BASE_URL) {
            contentType(ContentType.Application.Json)
            setBody(createTaskRequest)
        }.body()
    }

    suspend fun updateTask(taskId: String, updateTaskRequest: UpdateTaskRequest) {
        httpClient.put("$BASE_URL/$taskId") {
            contentType(ContentType.Application.Json)
            setBody(updateTaskRequest)
        }
    }

    suspend fun getTask(id: String): TaskDto {
        return httpClient.get("$BASE_URL/$id") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getTasks(offset: Long): GetTasksResponse {
        return httpClient.get(BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("offset", offset)
        }.body()
    }

    suspend fun findTask(text: String): GetTasksResponse {
        return httpClient.get("${Constants.BASE_URL}/search/orders") {
            contentType(ContentType.Application.Json)
            parameter("text", text)
        }.body()
    }
}