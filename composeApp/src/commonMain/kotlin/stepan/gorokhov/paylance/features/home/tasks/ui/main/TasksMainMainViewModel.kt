package stepan.gorokhov.paylance.features.home.tasks.ui.main

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
import stepan.gorokhov.paylance.features.home.tasks.domain.TasksRepository
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task
import stepan.gorokhov.viboranet.core.flow.mapState

class TasksMainMainViewModel(
    private val tasksRepository: TasksRepository,
    private val userRepository: UserRepository
) : ViewModel(), TasksMainPresenter {
    private val _state = MutableStateFlow(TasksMainViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<TasksMainEffect>()
    val effect = _effect.asSharedFlow()

    override fun loadTasks() {
        if (_state.value.user == null) {
            loadUser()
        }
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.Default) {
            val tasksOffset = _state.value.tasks.size.toLong()
            tasksRepository.getTasks(tasksOffset).onSuccess { tasks ->
                val previews = tasks.map { it.toPreview() }
                _state.update { it.copy(tasks = (it.tasks + previews).toImmutableList()) }
            }.onFailure {
                _state.update { it.copy(error = ErrorMessage("")) }
            }
            _state.update { it.copy(tasksLoaded = true) }
        }
    }

    override fun onProfileClicked() {
        viewModelScope.launch {
            _effect.emit(TasksMainEffect.NavigateProfile)
        }
    }

    override fun onTaskClicked(id: String) {
        viewModelScope.launch {
            _effect.emit(TasksMainEffect.NavigateTask(id))
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

private data class TasksMainViewModelState(
    val isLoading: Boolean = false,
    val tasksLoaded: Boolean = false,
    val tasks: ImmutableList<TaskPreview> = persistentListOf(),
    val user: User? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): TasksMainState {
        return when {
            user == null ||
                    !tasksLoaded -> TasksMainState.Loading

            error != null -> TasksMainState.Error

            else -> TasksMainState.TasksLoaded(
                tasks = tasks,
                user = user
            )
        }
    }
}