package stepan.gorokhov.paylance.features.home.profile.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.profile.ui.main.toVO
import stepan.gorokhov.viboranet.core.flow.mapState

class EditProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel(), EditProfilePresenter {
    private val _state = MutableStateFlow(EditProfileViewModelState())
    val state: StateFlow<EditProfileState> = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<EditProfileEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<EditProfileEffect> = _effect.asSharedFlow()

    override fun loadProfile() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            userRepository.getUser().onSuccess { user ->
                _state.update { it.copy(profile = user.toEditProfileVO()) }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        error = ErrorMessage(error.message ?: "Не удалось загрузить профиль")
                    )
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun navigateBack() {
        _effect.tryEmit(EditProfileEffect.NavigateBack)
    }

    override fun saveProfile(name: String) {
        val state = _state.value
        if (state.isSaving || state.profile == null) return

        _state.update { it.copy(isSaving = true) }
        viewModelScope.launch {
            userRepository.updateUser(state.profile.toUser()).onSuccess {
                _effect.emit(EditProfileEffect.NavigateBack)
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        error = ErrorMessage(
                            error.message ?: "Не удалось сохранить профиль"
                        )
                    )
                }
            }
            _state.update { it.copy(isSaving = false) }
        }
    }
}

private data class EditProfileViewModelState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val profile: EditProfileVO? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): EditProfileState {
        return when {
            isLoading -> EditProfileState.Loading
            error != null -> EditProfileState.Error(error)
            profile != null -> EditProfileState.ProfileLoaded(
                profile = profile,
                isSaving = isSaving
            )

            else -> EditProfileState.Idle
        }
    }
} 