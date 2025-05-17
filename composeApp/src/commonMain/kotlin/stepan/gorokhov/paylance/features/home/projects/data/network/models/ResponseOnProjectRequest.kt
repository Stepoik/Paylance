package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseOnProjectRequest(
    @SerialName("project_id")
    val projectId: String
)
