package stepan.gorokhov.paylance.features.home.projects.ui.createProject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.datetime.LocalDateTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository

class CreateProjectViewModel(
    private val projectsRepository: ProjectsRepository
) : ViewModel(), CreateProjectPresenter {
    private val _state = MutableStateFlow(CreateProjectState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateProjectEffect>()
    val effect = _effect.asSharedFlow()

    override fun setTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    override fun setDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    override fun setBudget(budget: String) {
        _state.update { it.copy(budget = budget) }
    }

    override fun setDeadline(date: LocalDateTime) {
        _state.update { it.copy(deadline = date) }
    }

    override fun onClickCreate() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            projectsRepository.createProject(_state.value.toNewProject()).onSuccess {
                _effect.emit(CreateProjectEffect.NavigateProject(it.id))
            }.onFailure {
                _state.update { it.copy() }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}