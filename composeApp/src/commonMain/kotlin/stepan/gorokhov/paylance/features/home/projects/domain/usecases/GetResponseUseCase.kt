package stepan.gorokhov.paylance.features.home.projects.domain.usecases

import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectResponseRepository
import stepan.gorokhov.paylance.features.home.projects.domain.models.FullResponse

class GetResponseUseCase(
    private val projectResponseRepository: ProjectResponseRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(responseId: String): Result<FullResponse> {
        val response = projectResponseRepository.getResponseById(responseId)
            .getOrElse { return Result.failure(it) }
        val freelancerInfo = userRepository.getFreelancerById(response.freelancerId)
            .getOrElse { return Result.failure(it) }
        return Result.success(
            FullResponse(
                response = response,
                info = freelancerInfo
            )
        )
    }
}
