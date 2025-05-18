package stepan.gorokhov.paylance.features.home.chats.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import stepan.gorokhov.paylance.core.time.now
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.chats.domain.ChatsRepository
import stepan.gorokhov.paylance.features.home.chats.domain.models.NewMessage
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.viboranet.core.flow.mapState

class ChatViewModel(
    private val chatId: String,
    private val chatsRepository: ChatsRepository,
    private val userRepository: UserRepository
) : ViewModel(), ChatPresenter {

    private val _state = MutableStateFlow(ChatViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<ChatEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<ChatEffect> = _effect.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            chatsRepository.subscribeOnChat(chatId).collect { message ->
                _state.update { state ->
                    val userId = userRepository.getUser().getOrNull()?.id ?: ""
                    state.copy(
                        messages = (state.messages + message.toVO(userId)).toImmutableList()
                    )
                }
            }
        }
    }

    override fun loadChat() {
        if (_state.value.isLoadingChat) return

        _state.update { it.copy(isLoadingChat = true) }
        viewModelScope.launch {
            chatsRepository.getChat(chatId)
                .onSuccess { chat ->
                    _state.update { it.copy(chat = chat.toVO()) }
                }
            _state.update { it.copy(isLoadingChat = false) }
        }
    }

    override fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(ChatEffect.NavigateBack)
        }
    }

    override fun loadMessages() {
        val state = _state.value
        if (state.isLoadingMessages) return

        _state.update { it.copy(isLoadingMessages = true) }
        viewModelScope.launch {
            val offset = state.messages.size.toLong()
            val userId = userRepository.getUser().getOrNull()?.id ?: ""
            chatsRepository.getMessages(chatId = chatId, offset = offset).onSuccess { messages ->
                _state.update {
                    val newMessages =
                        (messages.map { it.toVO(userId) } + it.messages).toImmutableList()
                    it.copy(messages = newMessages)
                }
            }
            _state.update { it.copy(isLoadingMessages = false) }
        }
    }

    override fun sendMessage(text: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val state = _state.value
            val newMessage = NewMessage(chatId = chatId, text = text)
            chatsRepository.sendMessage(newMessage)
                .onSuccess { message -> }
                .onFailure {
                    _state.update { it.copy(messages = state.messages) }
                }
        }
    }
}

private data class ChatViewModelState(
    val isLoadingMessages: Boolean = false,
    val isLoadingChat: Boolean = false,
    val messages: ImmutableList<MessageVO> = persistentListOf(),
    val chat: ChatVO? = null,
    val error: ErrorMessage? = null
) {
    fun toScreenState(): ChatState {
        return when {
            isLoadingChat || isLoadingMessages && messages.isEmpty() -> ChatState.Loading
            error != null && messages.isEmpty() -> ChatState.Error(error)
            chat != null -> ChatState.ChatLoaded(chat = chat, messages = messages)
            else -> ChatState.Idle
        }
    }
}