package stepan.gorokhov.paylance.features.home.profile.ui.freelancerEdit

import stepan.gorokhov.paylance.features.home.profile.domain.models.FreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.NewFreelancerInfo

fun FreelancerInfo.toVO(): FreelancerEditVO {
    return FreelancerEditVO(
        id = freelancerId,
        description = description,
        skills = skills
    )
}

fun FreelancerEditVO.toNewFreelancerInfo() = NewFreelancerInfo(
    description = description,
    skills = skills
)