package stepan.gorokhov.paylance.features.home.chats.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import stepan.gorokhov.paylance.features.home.chats.data.network.models.ChatDto
import stepan.gorokhov.paylance.features.home.chats.data.network.models.CreateMessageRequest
import stepan.gorokhov.paylance.features.home.chats.data.network.models.GetChatsResponse
import stepan.gorokhov.paylance.features.home.chats.data.network.models.GetMessagesResponse
import stepan.gorokhov.paylance.network.Constants

private const val BASE_URL = "${Constants.BASE_URL}/chats"

class ChatApi(
    private val httpClient: HttpClient
) {
    suspend fun getChats(offset: Long): GetChatsResponse {
        return httpClient.get(BASE_URL) {
            parameter("offset", offset)
        }.body()
    }

    suspend fun getChat(chatId: String): ChatDto {
        return httpClient.get("$BASE_URL/$chatId").body()
    }

    suspend fun sendMessage(chatId: String, request: CreateMessageRequest) {
        httpClient.post("$BASE_URL/$chatId/messages") {
            setBody(request)
        }
    }

    suspend fun getMessages(chatId: String, offset: Long): GetMessagesResponse {
        return httpClient.get("$BASE_URL/$chatId/messages"){
            parameter("offset", offset)
        }.body()
    }
}