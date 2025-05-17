package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProjectRequest(
    val title: String,
    val description: String,
    val budget: Double,
    val deadline: LocalDateTime
)