package stepan.gorokhov.paylance.features.home.projects.domain.models

import stepan.gorokhov.paylance.features.home.profile.domain.models.FreelancerInfo

data class FullResponse(
    val response: ProjectResponse,
    val info: FreelancerInfo
)
