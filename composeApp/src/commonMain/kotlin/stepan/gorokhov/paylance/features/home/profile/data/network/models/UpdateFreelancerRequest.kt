package stepan.gorokhov.paylance.features.home.profile.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateFreelancerRequest(
    val description: String,
    val skills: List<String>
)
