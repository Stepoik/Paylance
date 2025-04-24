package stepan.gorokhov.paylance.features.home.tasks.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GetTasksResponse(
    val orders: List<TaskDto>
)
