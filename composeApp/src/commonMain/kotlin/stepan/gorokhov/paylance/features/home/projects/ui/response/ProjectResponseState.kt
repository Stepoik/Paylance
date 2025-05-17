package stepan.gorokhov.paylance.features.home.projects.ui.response

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed interface ProjectResponseState {
    data object Idle : ProjectResponseState
    data object Loading : ProjectResponseState
    data class Error(val error: ErrorMessage) : ProjectResponseState
    data class ResponseLoaded(
        val response: ProjectResponseVO,
        val isAccepting: Boolean = false,
        val isRejecting: Boolean = false
    ) : ProjectResponseState
}

sealed interface ProjectResponseEffect {
    data object NavigateBack : ProjectResponseEffect
}

data class ProjectResponseVO(
    val id: String,
    val freelancerName: String,
    val freelancerRating: String,
    val freelancerSkills: List<String>
)