package stepan.gorokhov.paylance.features.home.profile.ui.main

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed interface ProfileState {
    data object Idle : ProfileState
    data object Loading : ProfileState
    data class Error(val error: ErrorMessage) : ProfileState
    data class ProfileLoaded(
        val profile: ProfileVO
    ) : ProfileState
}

sealed interface ProfileEffect {
    data object NavigateToEditProfile : ProfileEffect
    data object NavigateToEditFreelancerProfile : ProfileEffect
}

data class ProfileVO(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String?
)

interface ProfilePresenter {
    fun loadProfile()
    fun navigateToEditProfile()
    fun navigateToEditFreelancerProfile()
    fun logout()
} 