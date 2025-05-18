package stepan.gorokhov.paylance.features.home.chats.data

import stepan.gorokhov.paylance.features.home.chats.data.network.models.ChatDto
import stepan.gorokhov.paylance.features.home.chats.data.network.models.MessageDto
import stepan.gorokhov.paylance.features.home.chats.domain.models.Chat
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage

fun ChatDto.toChat(): Chat {
    return Chat(
        id = id,
        title = title,
        lastMessage = lastMessage?.toDomain()
    )
}

fun MessageDto.toDomain(): ChatMessage {
    return ChatMessage(
        id = id,
        text = text,
        userId = senderId,
        chatId = chatId,
        createdAt = createdAt
    )
}