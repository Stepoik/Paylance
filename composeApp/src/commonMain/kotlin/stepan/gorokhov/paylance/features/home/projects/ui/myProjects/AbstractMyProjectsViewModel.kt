package stepan.gorokhov.paylance.features.home.projects.ui.myProjects

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
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project
import stepan.gorokhov.paylance.features.home.projects.ui.common.ProjectPreview
import stepan.gorokhov.paylance.features.home.projects.ui.common.toPreview
import stepan.gorokhov.viboranet.core.flow.mapState

abstract class AbstractMyProjectsViewModel : ViewModel(), MyProjectsPresenter {

    private val _state = MutableStateFlow(MyProjectsViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<MyProjectsEffect>()
    val effect = _effect.asSharedFlow()

    override fun loadProjects() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.Default) {
            val projectsOffset = _state.value.projects.size.toLong()
            getProjects(projectsOffset).onSuccess { projects ->
                val previews = projects.map { it.toPreview() }
                _state.update { it.copy(projects = (it.projects + previews).toImmutableList()) }
            }.onFailure {
                _state.update { it.copy(error = ErrorMessage("")) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun createProject() {
        viewModelScope.launch {
            _effect.emit(MyProjectsEffect.NavigateCreateProject)
        }
    }

    override fun openProject(id: String) {
        viewModelScope.launch {
            _effect.emit(MyProjectsEffect.NavigateProject(id))
        }
    }

    protected abstract suspend fun getProjects(offset: Long): Result<List<Project>>
}

private data class MyProjectsViewModelState(
    val isScreenLoading: Boolean = true,
    val isLoading: Boolean = false,
    val projects: ImmutableList<ProjectPreview> = persistentListOf(),
    val error: ErrorMessage? = null
) {
    fun toScreenState(): MyProjectsState {
        return when {
            isLoading && projects.isEmpty() -> MyProjectsState.Loading

            error != null && projects.isEmpty() -> MyProjectsState.Error(error)

            else -> MyProjectsState.ProjectsLoaded(
                projects = projects,
                errorMessage = error
            )
        }
    }
}