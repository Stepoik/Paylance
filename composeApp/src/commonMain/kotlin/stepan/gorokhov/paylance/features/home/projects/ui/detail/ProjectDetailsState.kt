package stepan.gorokhov.paylance.features.home.projects.ui.detail

import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectStatus

sealed class ProjectDetailsState {
    data object Idle : ProjectDetailsState()
    data object Loading : ProjectDetailsState()
    data class ProjectLoaded(
        val project: ProjectVO,
        val isResponding: Boolean
    ) : ProjectDetailsState()

    data class Error(val error: ErrorMessage) : ProjectDetailsState()
}

data class ProjectVO(
    val id: String,
    val title: String,
    val description: String,
    val budget: String,
    val clientName: String,
    val deadline: String,
    val workType: String,
    val skills: List<String>,
    val status: ProjectStatus,
    val isRespond: Boolean
)

sealed class ProjectDetailsEffect {
    data object NavigateBack : ProjectDetailsEffect()
}