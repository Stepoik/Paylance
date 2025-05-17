package stepan.gorokhov.paylance.features.home.profile.ui.edit

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed interface EditProfileState {
    data object Idle : EditProfileState
    data object Loading : EditProfileState
    data class Error(val error: ErrorMessage) : EditProfileState
    data class ProfileLoaded(
        val profile: EditProfileVO,
        val isSaving: Boolean = false
    ) : EditProfileState
}

sealed interface EditProfileEffect {
    data object NavigateBack : EditProfileEffect
}

data class EditProfileVO(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String?
)

interface EditProfilePresenter {
    fun loadProfile()
    fun navigateBack()
    fun saveProfile(name: String)
} 