package stepan.gorokhov.paylance.features.home.tasks.domain.models

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val author: Author
)
