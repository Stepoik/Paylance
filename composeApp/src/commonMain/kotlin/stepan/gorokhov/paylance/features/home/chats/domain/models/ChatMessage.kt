package stepan.gorokhov.paylance.features.home.chats.domain.models

import kotlinx.datetime.LocalDateTime

data class ChatMessage(
    val id: String,
    val text: String,
    val userId: String,
    val chatId: String,
    val createdAt: LocalDateTime
)
