package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GenerateDescriptionRequest(
    val prompt: String
)
