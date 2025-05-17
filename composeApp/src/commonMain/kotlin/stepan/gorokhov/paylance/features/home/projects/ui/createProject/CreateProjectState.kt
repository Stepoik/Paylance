package stepan.gorokhov.paylance.features.home.projects.ui.createProject

import kotlinx.datetime.LocalDateTime
import stepan.gorokhov.paylance.core.time.now
import stepan.gorokhov.paylance.features.home.projects.domain.models.NewProject

data class CreateProjectState(
    val isLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val budget: String = "",
    val deadline: LocalDateTime = LocalDateTime.now()
)

sealed class CreateProjectEffect {
    data class NavigateProject(val id: String) : CreateProjectEffect()
    data object NavigateBack : CreateProjectEffect()
}

fun CreateProjectState.toNewProject(): NewProject {
    return NewProject(
        title = title,
        description = description,
        budget = budget.toDoubleOrNull() ?: 0.0,
        deadline = deadline
    )
}