package stepan.gorokhov.paylance.features.home.projects.ui.response

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
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectResponseRepository
import stepan.gorokhov.paylance.features.home.projects.domain.models.ReplyType
import stepan.gorokhov.paylance.features.home.projects.domain.usecases.GetResponseUseCase
import stepan.gorokhov.viboranet.core.flow.mapState

class ProjectResponseViewModel(
    private val responseId: String,
    private val getResponseUseCase: GetResponseUseCase,
    private val responseRepository: ProjectResponseRepository
) : ViewModel(), ProjectResponsePresenter {
    private val _state = MutableStateFlow(ProjectResponseViewModelState())
    val state: StateFlow<ProjectResponseState> = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ProjectResponseEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<ProjectResponseEffect> = _effect.asSharedFlow()

    override fun loadResponse() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getResponseUseCase.execute(responseId).onSuccess { response ->
                _state.update { it.copy(response = response.toVO()) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun navigateBack() {
        _effect.tryEmit(ProjectResponseEffect.NavigateBack)
    }

    override fun acceptResponse() {
        viewModelScope.launch {
            respond(ReplyType.ACCEPT)
        }
    }

    override fun rejectResponse() {
        viewModelScope.launch {
            respond(ReplyType.REJECT)
        }
    }

    private suspend fun respond(type: ReplyType) {
        if (_state.value.isResponding) return

        _state.update { it.copy(isResponding = true) }
        responseRepository.replyOnResponse(responseId = responseId, replyType = type)
        _state.update { it.copy(isResponding = false) }
    }
}

private data class ProjectResponseViewModelState(
    val isLoading: Boolean = false,
    val isResponding: Boolean = false,
    val response: ProjectResponseVO? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): ProjectResponseState {
        return when {
            isLoading -> ProjectResponseState.Loading
            error != null -> ProjectResponseState.Error(error)
            response != null -> ProjectResponseState.ResponseLoaded(response)
            else -> ProjectResponseState.Idle
        }
    }
}