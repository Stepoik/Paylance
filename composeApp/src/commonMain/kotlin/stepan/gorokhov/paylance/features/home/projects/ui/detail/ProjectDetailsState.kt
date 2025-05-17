package stepan.gorokhov.paylance.features.home.projects.ui.detail

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed class ProjectDetailsState {
    data object Idle : ProjectDetailsState()
    data object Loading : ProjectDetailsState()
    data class ProjectLoaded(val project: ProjectVO) : ProjectDetailsState()
    data class Error(val error: ErrorMessage) : ProjectDetailsState()
}

data class ProjectVO(
    val title: String,
    val description: String,
    val budget: String
)

sealed class ProjectDetailsEffect {

}