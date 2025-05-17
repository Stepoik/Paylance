package stepan.gorokhov.paylance.features.home.projects.domain.models

import kotlinx.datetime.LocalDateTime

data class Project(
    val id: String,
    val title: String,
    val description: String,
    val budget: Double,
    val author: Author,
    val createdAt: LocalDateTime,
    val deadline: LocalDateTime,
    val status: ProjectStatus,
    val skills: List<String>,
    val isRespond: Boolean
)


enum class ProjectStatus {
    OPEN,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
