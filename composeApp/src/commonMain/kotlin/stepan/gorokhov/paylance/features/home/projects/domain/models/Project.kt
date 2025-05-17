package stepan.gorokhov.paylance.features.home.projects.domain.models

import kotlinx.datetime.LocalDateTime

data class Project(
    val id: String,
    val title: String,
    val description: String,
    val budget: Double,
    val author: Author,
    val createdAt: LocalDateTime
)
