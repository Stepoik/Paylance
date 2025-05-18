package stepan.gorokhov.paylance.features.home.projects.ui.generateDescription

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed class GenerateDescriptionState {
    data object Loading : GenerateDescriptionState()
    data class DescriptionGenerated(
        val prompt: String,
        val title: String?,
        val description: String?
    ) : GenerateDescriptionState()
    data class Error(val error: ErrorMessage) : GenerateDescriptionState()
}

sealed class GenerateDescriptionEffect {
    data object NavigateBack : GenerateDescriptionEffect()
    data class NavigateToCreateProject(
        val title: String,
        val description: String
    ) : GenerateDescriptionEffect()
} 