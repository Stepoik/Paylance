package stepan.gorokhov.paylance.features.home.projects.ui.createProject

import kotlinx.datetime.LocalDateTime
import stepan.gorokhov.paylance.core.time.now
import stepan.gorokhov.paylance.features.home.projects.domain.models.NewProject

data class CreateProjectState(
    val title: String = "",
    val description: String = "",
    val budget: String = "",
    val skills: List<String> = listOf(),
    val deadline: LocalDateTime = LocalDateTime.now(),
    val isCreating: Boolean = false,
)

sealed interface CreateProjectEffect {
    data object NavigateBack : CreateProjectEffect
    data class NavigateProject(val id: String) : CreateProjectEffect
    data object NavigateGenerateProject : CreateProjectEffect
}

fun CreateProjectState.toNewProject(): NewProject {
    return NewProject(
        title = title,
        description = description,
        budget = budget.toDoubleOrNull() ?: 0.0,
        deadline = deadline,
        skills = skills
    )
}