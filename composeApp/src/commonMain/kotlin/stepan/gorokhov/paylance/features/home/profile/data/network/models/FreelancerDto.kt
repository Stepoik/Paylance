package stepan.gorokhov.paylance.features.home.profile.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class FreelancerDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val skills: List<String>,
    val rating: Double
)