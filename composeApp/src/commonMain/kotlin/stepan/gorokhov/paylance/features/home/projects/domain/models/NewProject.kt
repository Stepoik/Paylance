package stepan.gorokhov.paylance.features.home.projects.domain.models

import kotlinx.datetime.LocalDateTime

data class NewProject(
    val title: String,
    val description: String,
    val budget: Double,
    val deadline: LocalDateTime
)
