package stepan.gorokhov.paylance.features.home.projects.data

import stepan.gorokhov.paylance.features.home.projects.data.network.ResponseApi
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ReplyOnResponseRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ResponseOnProjectRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.toDomain
import stepan.gorokhov.paylance.features.home.projects.data.network.toDto
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectResponseRepository
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectResponse
import stepan.gorokhov.paylance.features.home.projects.domain.models.ReplyType

class ProjectResponseRepositoryImpl(
    private val responseApi: ResponseApi
) : ProjectResponseRepository {
    override suspend fun responseOnProject(projectId: String): Result<Any?> {
        return runCatching {
            val request = ResponseOnProjectRequest(projectId = projectId)
            responseApi.responseOnProject(request)
        }
    }

    override suspend fun getResponseById(responseId: String): Result<ProjectResponse> {
        return runCatching {
            responseApi.getResponseById(responseId).toDomain()
        }
    }

    override suspend fun replyOnResponse(
        responseId: String,
        replyType: ReplyType
    ): Result<ReplyType> {
        return runCatching {
            val request = ReplyOnResponseRequest(
                responseId = responseId,
                replyType = replyType.toDto()
            )
            responseApi.replyOnResponse(request)
            replyType
        }
    }
}