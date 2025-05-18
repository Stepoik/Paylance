package stepan.gorokhov.paylance.features.home.projects.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.skip
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.profile.domain.models.User
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.paylance.features.home.projects.ui.common.ProjectPreview
import stepan.gorokhov.paylance.features.home.projects.ui.common.toPreview
import stepan.gorokhov.viboranet.core.flow.mapState

@OptIn(FlowPreview::class)
class ProjectsMainViewModel(
    private val projectsRepository: ProjectsRepository,
    private val userRepository: UserRepository
) : ViewModel(), ProjectsMainPresenter {
    private val _state = MutableStateFlow(ProjectsMainViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ProjectsMainEffect>()
    val effect = _effect.asSharedFlow()

    private val searchTextFlow = MutableStateFlow("")
    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            searchTextFlow.drop(1).debounce(SEARCH_DEBOUNCE).collect {
                loadProjects(forced = true)
            }
        }
    }

    override fun loadProjects(forced: Boolean) {
        if (_state.value.user == null) {
            loadUser()
        }
        val state = _state.value
        if (state.isLoading && !forced) return

        _state.update { it.copy(isLoading = true, error = null) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.Default) {
            val projectsOffset = if (forced) 0 else state.projects.size.toLong()
            val text = state.searchText
            val projectsResult = if (text.isNotEmpty()) {
                projectsRepository.findProjects(text = text, offset = projectsOffset)
            } else {
                projectsRepository.getProjects(projectsOffset)
            }

            projectsResult.onSuccess { projects ->
                val previews = projects.map { it.toPreview() }
                val newProjects = if (forced) previews else (state.projects + previews)
                _state.update { it.copy(projects = newProjects.toImmutableList()) }
            }.onFailure {
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

    override fun setSearchText(text: String) {
        _state.update { it.copy(searchText = text) }
        searchTextFlow.value = text
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

    companion object {
        private const val SEARCH_DEBOUNCE = 300L
    }
}

private data class ProjectsMainViewModelState(
    val searchText: String = "",
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

            error != null && projects.isEmpty() -> ProjectsMainState.Error(error)

            else -> ProjectsMainState.ProjectsLoaded(
                projects = projects,
                user = user,
                searchText = searchText
            )
        }
    }
}