package stepan.gorokhov.paylance.features.home.projects.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import stepan.gorokhov.paylance.features.home.projects.data.network.models.CreateProjectRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.models.GetProjectsResponse
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ProjectDto
import stepan.gorokhov.paylance.features.home.projects.data.network.models.UpdateProjectRequest
import stepan.gorokhov.paylance.network.Constants

private const val BASE_URL = "${Constants.BASE_URL}/projects"

class ProjectsApi(
    private val httpClient: HttpClient
) {
    suspend fun createProject(createProjectRequest: CreateProjectRequest): ProjectDto {
        return httpClient.post(BASE_URL) {
            contentType(ContentType.Application.Json)
            setBody(createProjectRequest)
        }.body()
    }

    suspend fun updateProject(id: String, updateProjectRequest: UpdateProjectRequest) {
        httpClient.put("$BASE_URL/$id") {
            contentType(ContentType.Application.Json)
            setBody(updateProjectRequest)
        }
    }

    suspend fun getClientProjects(offset: Long): GetProjectsResponse {
        return httpClient.get("$BASE_URL/client") {
            parameter("offset", offset)
        }.body()
    }

    suspend fun getFreelancerProjects(offset: Long): GetProjectsResponse {
        return httpClient.get("$BASE_URL/freelancer") {
            parameter("offset", offset)
        }.body()
    }

    suspend fun getProject(id: String): ProjectDto {
        return httpClient.get("$BASE_URL/$id") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getProjects(offset: Long): GetProjectsResponse {
        return httpClient.get(BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("offset", offset)
        }.body()
    }

    suspend fun findProject(text: String, offset: Long): GetProjectsResponse {
        return httpClient.get("$BASE_URL/search") {
            contentType(ContentType.Application.Json)
            parameter("q", text)
            parameter("offset", offset)
        }.body()
    }

    suspend fun closeProject(id: String) {
        httpClient.post("$BASE_URL/$id/close")
    }
}