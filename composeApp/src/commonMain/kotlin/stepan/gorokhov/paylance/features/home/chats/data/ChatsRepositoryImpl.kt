package stepan.gorokhov.paylance.features.home.chats.data

import stepan.gorokhov.paylance.features.home.chats.data.network.ChatApi
import stepan.gorokhov.paylance.features.home.chats.data.network.models.CreateMessageRequest
import stepan.gorokhov.paylance.features.home.chats.domain.ChatsRepository
import stepan.gorokhov.paylance.features.home.chats.domain.models.Chat
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage
import stepan.gorokhov.paylance.features.home.chats.domain.models.NewMessage

class ChatsRepositoryImpl(
    private val chatApi: ChatApi
) : ChatsRepository {
    override suspend fun getChats(offset: Long): Result<List<Chat>> {
        return runCatching {
            chatApi.getChats(offset).chats.map { it.toChat() }
        }
    }

    override suspend fun getChat(id: String): Result<Chat> {
        return runCatching {
            chatApi.getChat(id).toChat()
        }
    }

    override suspend fun sendMessage(message: NewMessage): Result<Any?> {
        return runCatching {
            val request = CreateMessageRequest(text = message.text)
            chatApi.sendMessage(chatId = message.chatId, request = request)
        }
    }

    override suspend fun getMessages(chatId: String, offset: Long): Result<List<ChatMessage>> {
        return runCatching {
            chatApi.getMessages(chatId = chatId, offset = offset).messages.map { it.toDomain() }
        }
    }
}