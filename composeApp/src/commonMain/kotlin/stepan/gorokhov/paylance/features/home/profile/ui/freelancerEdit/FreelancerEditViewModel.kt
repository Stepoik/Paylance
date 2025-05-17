package stepan.gorokhov.paylance.features.home.profile.ui.freelancerEdit

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
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.viboranet.core.flow.mapState

class FreelancerEditViewModel(
    private val userRepository: UserRepository
) : ViewModel(), FreelancerEditPresenter {
    private val _state = MutableStateFlow(FreelancerEditViewModelState())
    val state: StateFlow<FreelancerEditState> = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<FreelancerEditEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<FreelancerEditEffect> = _effect.asSharedFlow()

    override fun loadProfile() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            userRepository.getFreelancer().onSuccess { user ->
                _state.update { it.copy(profile = user.toVO()) }
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
        _effect.tryEmit(FreelancerEditEffect.NavigateBack)
    }

    override fun addSkill(skill: String) {
        val currentProfile = _state.value.profile ?: return
        val updatedSkills = currentProfile.skills + skill
        _state.update {
            it.copy(profile = currentProfile.copy(skills = updatedSkills))
        }
    }

    override fun removeSkill(skill: String) {
        val currentProfile = _state.value.profile ?: return
        val updatedSkills = currentProfile.skills - skill
        _state.update {
            it.copy(profile = currentProfile.copy(skills = updatedSkills))
        }
    }

    override fun setDescription(description: String) {
        _state.update { it.copy(profile = it.profile?.copy(description = description)) }
    }

    override fun saveProfile() {
        val state = _state.value
        if (state.isSaving || state.profile == null) return

        _state.update { it.copy(isSaving = true) }
        viewModelScope.launch {
            userRepository.updateFreelancer(state.profile.toNewFreelancerInfo()).onSuccess {
                _effect.emit(FreelancerEditEffect.NavigateBack)
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

private data class FreelancerEditViewModelState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val profile: FreelancerEditVO? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): FreelancerEditState {
        return when {
            isLoading -> FreelancerEditState.Loading
            error != null -> FreelancerEditState.Error(error)
            profile != null -> FreelancerEditState.ProfileLoaded(
                profile = profile,
                isSaving = isSaving
            )

            else -> FreelancerEditState.Idle
        }
    }
} 