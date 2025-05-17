package stepan.gorokhov.paylance.features.home.projects.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.profile.domain.models.User
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.paylance.features.home.projects.ui.common.ProjectPreview
import stepan.gorokhov.paylance.features.home.projects.ui.common.toPreview
import stepan.gorokhov.viboranet.core.flow.mapState

class ProjectsMainViewModel(
    private val projectsRepository: ProjectsRepository,
    private val userRepository: UserRepository
) : ViewModel(), ProjectsMainPresenter {
    private val _state = MutableStateFlow(ProjectsMainViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ProjectsMainEffect>()
    val effect = _effect.asSharedFlow()

    override fun loadProjects() {
        if (_state.value.user == null) {
            loadUser()
        }
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.Default) {
            val projectsOffset = _state.value.projects.size.toLong()
            projectsRepository.getProjects(projectsOffset).onSuccess { projects ->
                val previews = projects.map { it.toPreview() }
                _state.update { it.copy(projects = (it.projects + previews).toImmutableList()) }
            }.onFailure {
                println(it)
                _state.update { it.copy(error = ErrorMessage("")) }
            }
            _state.update { it.copy(projectsLoaded = true, isLoading = false) }
        }
    }

    override fun onProfileClicked() {
        viewModelScope.launch {
            _effect.emit(ProjectsMainEffect.NavigateProfile)
        }
    }

    override fun onProjectClicked(id: String) {
        viewModelScope.launch {
            _effect.emit(ProjectsMainEffect.NavigateProject(id))
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            userRepository.getUser().onFailure {
                _state.update {
                    it.copy(
                        user = null,
                        error = ErrorMessage("Ошибка при загрузке пользователя")
                    )
                }
            }.onSuccess { user ->
                _state.update { it.copy(user = user) }
            }
        }
    }
}

private data class ProjectsMainViewModelState(
    val isLoading: Boolean = false,
    val projectsLoaded: Boolean = false,
    val projects: ImmutableList<ProjectPreview> = persistentListOf(),
    val user: User? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): ProjectsMainState {
        return when {
            user == null ||
                    !projectsLoaded -> ProjectsMainState.Loading

            error != null -> ProjectsMainState.Error

            else -> ProjectsMainState.ProjectsLoaded(
                projects = projects,
                user = user
            )
        }
    }
}