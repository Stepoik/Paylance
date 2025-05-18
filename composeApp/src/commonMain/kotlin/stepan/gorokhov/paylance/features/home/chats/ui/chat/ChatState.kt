package stepan.gorokhov.paylance.features.home.chats.ui.chat

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed interface ChatState {
    data object Idle : ChatState
    data object Loading : ChatState
    data class Error(val error: ErrorMessage) : ChatState
    data class ChatLoaded(
        val chat: ChatVO,
        val messages: List<MessageVO>
    ) : ChatState
}

sealed interface ChatEffect {
    data object NavigateBack : ChatEffect
}

data class MessageVO(
    val id: String,
    val text: String,
    val time: String,
    val isOutgoing: Boolean
)

data class ChatVO(
    val id: String,
    val title: String,
)

interface ChatPresenter {
    fun loadChat()
    fun navigateBack()
    fun loadMessages()
    fun sendMessage(text: String)
} 