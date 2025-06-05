package stepan.gorokhov.paylance.features.home.projects.ui.createProject

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
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.getString
import paylance.composeapp.generated.resources.Res
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository

class CreateProjectViewModel(
    title: String,
    description: String,
    private val projectRepository: ProjectsRepository
) : ViewModel(), CreateProjectPresenter {
    private val _state =
        MutableStateFlow(CreateProjectState(title = title, description = description))
    val state: StateFlow<CreateProjectState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateProjectEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<CreateProjectEffect> = _effect.asSharedFlow()

    override fun setTitle(title: String) {
        _state.update { it.copy(title = title, error = null) }
    }

    override fun setDescription(description: String) {
        _state.update { it.copy(description = description, error = null) }
    }

    override fun setBudget(budget: String) {
        _state.update { it.copy(budget = budget, error = null) }
    }

    override fun setDeadline(date: LocalDateTime) {
        _state.update { it.copy(deadline = date, error = null) }
    }

    override fun addSkill(skill: String) {
        val currentSkills = _state.value.skills
        if (skill !in currentSkills) {
            _state.update { it.copy(skills = currentSkills + skill, error = null) }
        }
    }

    override fun removeSkill(skill: String) {
        val currentSkills = _state.value.skills
        _state.update { it.copy(skills = currentSkills - skill, error = null) }
    }

    override fun onClickGenerateProject() {
        _effect.tryEmit(CreateProjectEffect.NavigateGenerateProject)
    }

    override fun onClickCreate() {
        val state = _state.value
        if (state.isCreating) return

        if (state.title.isBlank() || state.description.isBlank() || state.budget.isBlank()) {
            _state.update { it.copy(error = ErrorMessage("Заполните пустые поля")) }
            return
        }

        _state.update { it.copy(isCreating = true) }
        viewModelScope.launch {
            projectRepository.createProject(state.toNewProject()).onSuccess { project ->
                _effect.emit(CreateProjectEffect.NavigateProject(project.id))
            }.onFailure { error ->
                _state.update { it.copy(error = ErrorMessage("Неизвестная ошибка при создании")) }
            }
            _state.update { it.copy(isCreating = false) }
        }
    }
}