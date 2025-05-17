package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectStatus

@Serializable
data class ProjectDto(
    val id: String,
    val author: AuthorDto,
    val title: String,
    val description: String,
    val budget: Double,
    val deadline: LocalDateTime,
    val status: ProjectStatusDto,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val isRespond: Boolean,
    val skills: List<String>
)

@Serializable
enum class ProjectStatusDto {
    OPEN,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}