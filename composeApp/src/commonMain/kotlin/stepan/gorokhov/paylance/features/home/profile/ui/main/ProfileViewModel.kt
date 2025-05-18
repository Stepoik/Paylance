package stepan.gorokhov.paylance.features.home.profile.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.auth.domain.AuthRepository
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.viboranet.core.flow.mapState

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel(), ProfilePresenter {
    private val _state = MutableStateFlow(ProfileViewModelState())
    val state: StateFlow<ProfileState> = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ProfileEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<ProfileEffect> = _effect.asSharedFlow()

    override fun loadProfile() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            userRepository.getUser().onSuccess { user ->
                _state.update { it.copy(profile = user.toVO()) }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        error = ErrorMessage(
                            error.message ?: "Не удалось загрузить профиль"
                        )
                    )
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun navigateToEditProfile() {
        _effect.tryEmit(ProfileEffect.NavigateToEditProfile)
    }

    override fun navigateToEditFreelancerProfile() {
        _effect.tryEmit(ProfileEffect.NavigateToEditFreelancerProfile)
    }

    override fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}

private data class ProfileViewModelState(
    val isLoading: Boolean = false,
    val profile: ProfileVO? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): ProfileState {
        return when {
            isLoading -> ProfileState.Loading
            error != null -> ProfileState.Error(error)
            profile != null -> ProfileState.ProfileLoaded(profile)
            else -> ProfileState.Idle
        }
    }
} 