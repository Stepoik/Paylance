package stepan.gorokhov.paylance.features.home.chats.ui.list

import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage

sealed interface ChatsListState {
    data object Idle : ChatsListState
    data object Loading : ChatsListState
    data class Error(val error: ErrorMessage) : ChatsListState
    data class ChatsLoaded(val chats: List<ChatVO>) : ChatsListState
}

sealed interface ChatsListEffect {
    data object NavigateBack : ChatsListEffect
    data class NavigateToChat(val chatId: String) : ChatsListEffect
}

data class ChatVO(
    val id: String,
    val name: String,
    val avatarUrl: String?,
    val message: MessageVO?
)

data class MessageVO(
    val text: String,
    val time: String
)

interface ChatsListPresenter {
    fun loadChats()
    fun navigateBack()
    fun openChat(chatId: String)
} 