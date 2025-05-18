package stepan.gorokhov.paylance.features.home.chats.domain.models

data class Chat(
    val id: String,
    val title: String,
    val lastMessage: ChatMessage?
)
