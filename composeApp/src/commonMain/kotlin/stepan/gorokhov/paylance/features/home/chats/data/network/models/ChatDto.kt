package stepan.gorokhov.paylance.features.home.chats.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ChatDto(
    val id: String,
    val projectId: String,
    val clientId: String,
    val title: String,
    val freelancerId: String,
    val lastMessage: MessageDto?,
    val unreadCount: Int
)