package stepan.gorokhov.paylance.features.home.projects.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectResponseRepository
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectStatus
import stepan.gorokhov.viboranet.core.flow.mapState

class ProjectDetailsViewModel(
    private val projectId: String,
    private val projectsRepository: ProjectsRepository,
    private val responseRepository: ProjectResponseRepository,
    private val userRepository: UserRepository
) : ViewModel(), ProjectDetailsPresenter {
    private val _state = MutableStateFlow(ProjectDetailsViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ProjectDetailsEffect>()
    val effect = _effect.asSharedFlow()

    override fun loadProject() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val currentUser = userRepository.getUser().getOrNull()?.id ?: ""
            projectsRepository.getProjectById(id = projectId).onSuccess { project ->
                _state.update { it.copy(project = project.toProjectVO(currentUser)) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun responseOnProject() {
        if (_state.value.isResponding) return
        
        val currentProject = _state.value.project
        if (currentProject?.status == ProjectStatus.CANCELLED) {
            _state.update { it.copy(error = ErrorMessage("Нельзя откликнуться на закрытый проект")) }
            return
        }

        _state.update { it.copy(isResponding = true) }
        viewModelScope.launch {
            responseRepository.responseOnProject(projectId).onSuccess {
                _state.update {
                    val newProject = it.project?.copy(
                        isRespond = true
                    )
                    it.copy(project = newProject)
                }
            }
            _state.update { it.copy(isResponding = false) }
        }
    }

    override fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(ProjectDetailsEffect.NavigateBack)
        }
    }

    override fun closeProject() {
        if (_state.value.isResponding) return

        _state.update { it.copy(isResponding = true) }
        viewModelScope.launch {
            projectsRepository.closeProject(projectId).onSuccess {
                loadProject()
            }.onFailure {
                _state.update { it.copy(error = ErrorMessage("Не удалось закрыть проект")) }
            }
            _state.update { it.copy(isResponding = false) }
        }
    }
}

private data class ProjectDetailsViewModelState(
    val project: ProjectVO? = null,
    val isLoading: Boolean = false,
    val isResponding: Boolean = false,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): ProjectDetailsState {
        return when {
            isLoading -> ProjectDetailsState.Loading
            error != null -> ProjectDetailsState.Error(error)
            project != null -> ProjectDetailsState.ProjectLoaded(
                project = project,
                isResponding = isResponding
            )

            else -> ProjectDetailsState.Idle
        }
    }
}