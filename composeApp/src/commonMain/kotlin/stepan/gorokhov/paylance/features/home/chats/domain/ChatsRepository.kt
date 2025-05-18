package stepan.gorokhov.paylance.features.home.chats.domain

import stepan.gorokhov.paylance.features.home.chats.domain.models.Chat
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage
import stepan.gorokhov.paylance.features.home.chats.domain.models.NewMessage

interface ChatsRepository {
    suspend fun getChats(offset: Long): Result<List<Chat>>

    suspend fun getChat(id: String): Result<Chat>

    suspend fun sendMessage(message: NewMessage): Result<Any?>

    suspend fun getMessages(chatId: String, offset: Long): Result<List<ChatMessage>>
}