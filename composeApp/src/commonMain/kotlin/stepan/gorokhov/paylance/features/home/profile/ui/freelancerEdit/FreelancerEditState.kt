package stepan.gorokhov.paylance.features.home.profile.ui.freelancerEdit

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed interface FreelancerEditState {
    data object Idle : FreelancerEditState
    data object Loading : FreelancerEditState
    data class Error(val error: ErrorMessage) : FreelancerEditState
    data class ProfileLoaded(
        val profile: FreelancerEditVO,
        val isSaving: Boolean = false
    ) : FreelancerEditState
}

sealed interface FreelancerEditEffect {
    data object NavigateBack : FreelancerEditEffect
}

data class FreelancerEditVO(
    val id: String,
    val description: String,
    val skills: List<String>
)

interface FreelancerEditPresenter {
    fun loadProfile()
    fun navigateBack()
    fun addSkill(skill: String)
    fun removeSkill(skill: String)
    fun setDescription(description: String)
    fun saveProfile()
} 