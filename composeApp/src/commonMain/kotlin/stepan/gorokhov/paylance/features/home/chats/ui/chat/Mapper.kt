package stepan.gorokhov.paylance.features.home.chats.ui.chat

import stepan.gorokhov.paylance.core.time.formatHoursMinutes
import stepan.gorokhov.paylance.core.time.fromUTC
import stepan.gorokhov.paylance.features.home.chats.domain.models.Chat
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage

fun Chat.toVO() = ChatVO(
    id = id,
    title = title
)

fun ChatMessage.toVO(currentUserId: String) = MessageVO(
    id = id,
    text = text,
    time = createdAt.fromUTC().formatHoursMinutes(),
    isOutgoing = userId == currentUserId
)