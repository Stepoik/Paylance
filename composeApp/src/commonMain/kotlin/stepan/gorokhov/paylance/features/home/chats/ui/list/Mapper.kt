package stepan.gorokhov.paylance.features.home.chats.ui.list

import stepan.gorokhov.paylance.core.time.formatHoursMinutes
import stepan.gorokhov.paylance.core.time.fromUTC
import stepan.gorokhov.paylance.features.home.chats.domain.models.Chat
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage

fun Chat.toVO(): ChatVO {
    return ChatVO(
        id = id,
        name = title,
        avatarUrl = null,
        message = lastMessage?.toPreviewVO()
    )
}

fun ChatMessage.toPreviewVO() = MessageVO(
    text = text,
    time = createdAt.fromUTC().formatHoursMinutes()
)