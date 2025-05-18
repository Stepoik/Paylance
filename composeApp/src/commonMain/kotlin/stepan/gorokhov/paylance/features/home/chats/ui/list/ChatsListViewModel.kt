package stepan.gorokhov.paylance.features.home.chats.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.chats.domain.ChatsRepository
import stepan.gorokhov.viboranet.core.flow.mapState

class ChatsListViewModel(
    private val chatsRepository: ChatsRepository
) : ViewModel(), ChatsListPresenter {

    private val _state = MutableStateFlow(ChatsListViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ChatsListEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<ChatsListEffect> = _effect.asSharedFlow()

    override fun loadChats() {
        val state = _state.value
        if (state.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            chatsRepository.getChats(state.chats.size.toLong())
                .onSuccess { chats ->
                    val newChats = (state.chats + chats.map { it.toVO() }).toImmutableList()
                    _state.update { it.copy(chats = newChats) }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(ChatsListEffect.NavigateBack)
        }
    }

    override fun openChat(chatId: String) {
        viewModelScope.launch {
            _effect.emit(ChatsListEffect.NavigateToChat(chatId))
        }
    }
}

private data class ChatsListViewModelState(
    val isLoading: Boolean = false,
    val chats: ImmutableList<ChatVO> = persistentListOf(),
    val error: ErrorMessage? = null
) {
    fun toScreenState(): ChatsListState {
        return when {
            isLoading && chats.isEmpty() -> ChatsListState.Loading
            error != null && chats.isEmpty() -> ChatsListState.Error(error)
            else -> ChatsListState.ChatsLoaded(chats)
        }
    }
}