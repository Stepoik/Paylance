package stepan.gorokhov.paylance.features.home.profile.data

import dev.gitlive.firebase.auth.FirebaseUser
import stepan.gorokhov.paylance.features.home.profile.data.network.models.FreelancerDto
import stepan.gorokhov.paylance.features.home.profile.data.network.models.UpdateFreelancerRequest
import stepan.gorokhov.paylance.features.home.profile.domain.models.FreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.NewFreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.User

fun FirebaseUser.toDomain(): User {
    return User(
        id = uid,
        name = displayName ?: "",
        image = photoURL ?: "",
        email = email ?: "",
    )
}

fun FreelancerDto.toFreelancerInfo() = FreelancerInfo(
    freelancerId = id,
    name = name,
    imageUrl = imageUrl,
    description = description,
    skills = skills,
    rating = rating
)

fun NewFreelancerInfo.toUpdateFreelancerRequest(): UpdateFreelancerRequest {
    return UpdateFreelancerRequest(
        description = description,
        skills = skills
    )
}