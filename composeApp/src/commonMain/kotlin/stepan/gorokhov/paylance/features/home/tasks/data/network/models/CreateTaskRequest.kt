package stepan.gorokhov.paylance.features.home.tasks.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskRequest(
    val title: String,
    val description: String,
    val budget: Double,
    val deadline: LocalDateTime
)
