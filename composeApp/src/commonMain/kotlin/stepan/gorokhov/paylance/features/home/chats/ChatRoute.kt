package stepan.gorokhov.paylance.features.home.chats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.chats.ui.chat.ChatScreen
import stepan.gorokhov.paylance.features.home.chats.ui.list.ChatsListScreen

@Serializable
sealed class ChatRoute {
    @Serializable
    data object Chats : ChatRoute()
    @Serializable
    data class Chat(val id: String) : ChatRoute()
}

fun NavGraphBuilder.chat(navController: NavController) {
    navigation<HomeRoute.Chats>(startDestination = ChatRoute.Chats) {
        composable<ChatRoute.Chats> {
            ChatsListScreen(navController = navController)
        }

        composable<ChatRoute.Chat> {
            val id = it.toRoute<ChatRoute.Chat>().id
            ChatScreen(navController, chatId = id)
        }
    }
}