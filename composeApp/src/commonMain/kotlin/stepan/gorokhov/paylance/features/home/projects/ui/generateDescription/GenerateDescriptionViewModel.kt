package stepan.gorokhov.paylance.features.home.projects.ui.generateDescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.viboranet.core.flow.mapState

class GenerateDescriptionViewModel(
    private val projectsRepository: ProjectsRepository
) : ViewModel(), GenerateDescriptionPresenter {
    private val _state = MutableStateFlow(GenerateDescriptionViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<GenerateDescriptionEffect>()
    val effect = _effect.asSharedFlow()

    override fun setPrompt(prompt: String) {
        _state.update { it.copy(prompt = prompt) }
    }

    override fun generateDescription() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            projectsRepository.generateProjectDescription(_state.value.prompt)
                .onSuccess { (title, description) ->
                    _state.update {
                        it.copy(
                            title = title,
                            description = description
                        )
                    }
                }.onFailure {
                _state.update { it.copy(error = ErrorMessage("Не удалось сгенерировать описание")) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun acceptDescription() {
        val state = _state.value
        if (!state.title.isNullOrEmpty() && !state.description.isNullOrEmpty()) {
            viewModelScope.launch {
                _effect.emit(
                    GenerateDescriptionEffect.NavigateToCreateProject(
                        title = state.title,
                        description = state.description
                    )
                )
            }
        }
    }

    override fun rejectDescription() {
        _state.update {
            it.copy(
                title = null,
                description = null
            )
        }
    }

    override fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(GenerateDescriptionEffect.NavigateBack)
        }
    }
}

private data class GenerateDescriptionViewModelState(
    val prompt: String = "",
    val title: String? = null,
    val description: String? = null,
    val isLoading: Boolean = false,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): GenerateDescriptionState {
        return when {
            isLoading -> GenerateDescriptionState.Loading
            error != null -> GenerateDescriptionState.Error(error)
            else -> GenerateDescriptionState.DescriptionGenerated(
                title = title,
                description = description,
                prompt = prompt
            )
        }
    }
} 