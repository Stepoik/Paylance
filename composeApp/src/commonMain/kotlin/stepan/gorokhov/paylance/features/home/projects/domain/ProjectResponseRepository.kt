package stepan.gorokhov.paylance.features.home.projects.domain

import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectResponse
import stepan.gorokhov.paylance.features.home.projects.domain.models.ReplyType

interface ProjectResponseRepository {
    suspend fun responseOnProject(projectId: String): Result<Any?>

    suspend fun getResponseById(responseId: String): Result<ProjectResponse>

    suspend fun replyOnResponse(responseId: String, replyType: ReplyType): Result<ReplyType>
}