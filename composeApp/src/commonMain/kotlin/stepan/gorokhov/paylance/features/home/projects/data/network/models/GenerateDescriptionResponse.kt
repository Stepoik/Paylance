package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GenerateDescriptionResponse(
    val title: String,
    val description: String
)