package stepan.gorokhov.paylance.features.home.chats.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import stepan.gorokhov.paylance.features.home.chats.data.network.models.MessageDto
import stepan.gorokhov.paylance.features.home.chats.data.toDomain
import stepan.gorokhov.paylance.features.home.chats.domain.models.ChatMessage
import stepan.gorokhov.paylance.network.Constants

class ChatWebSocketStore(
    private val httpClient: HttpClient
) {
    fun connect(chatId: String): Flow<ChatMessage> {
        return flow {
            val url = "wss://${Constants.BASE_DOMAIN}/chats/$chatId/ws"
            val session = httpClient.webSocketSession(url)
            try {
                for (frame in session.incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val text = frame.readText()
                            try {
                                val chatMessage = Json.decodeFromString<MessageDto>(text)
                                emit(chatMessage.toDomain())
                            } catch (e: Exception) {
                                println(e)
                            }
                        }

                        else -> {}
                    }
                }
            } catch (e: Exception) {
                println(e)
                session.close()
            }
        }
    }
}