package stepan.gorokhov.paylance.features.home.chats.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String,
    val chatId: String,
    val senderId: String,
    val text: String,
    val createdAt: LocalDateTime,
    val isRead: Boolean
)