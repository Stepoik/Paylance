package stepan.gorokhov.paylance.features.home.tasks.domain.models

import kotlinx.datetime.LocalDateTime

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val author: Author,
    val createdAt: LocalDateTime
)
