package stepan.gorokhov.paylance.features.home.projects.ui.response

import stepan.gorokhov.paylance.features.home.projects.domain.models.FullResponse

fun FullResponse.toVO(): ProjectResponseVO {
    return ProjectResponseVO(
        id = response.id,
        freelancerName = info.name,
        freelancerRating = info.rating.toString(),
        freelancerSkills = info.skills
    )
}