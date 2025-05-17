package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDto(
    val id: String,
    val author: AuthorDto,
    val title: String,
    val description: String,
    val budget: Double,
    val deadline: LocalDateTime,
    val status: ProjectStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

@Serializable
enum class ProjectStatus {
    OPEN,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}