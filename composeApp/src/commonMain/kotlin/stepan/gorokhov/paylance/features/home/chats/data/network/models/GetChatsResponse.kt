package stepan.gorokhov.paylance.features.home.chats.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GetChatsResponse(
    val chats: List<ChatDto>
) 