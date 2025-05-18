package stepan.gorokhov.paylance.features.home.chats.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.chats.ChatRoute
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer

@Composable
fun ChatsListScreen(navController: NavController) {
    val viewModel = koinViewModel<ChatsListViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadChats()
        viewModel.effect.collect { effect ->
            when (effect) {
                is ChatsListEffect.NavigateBack -> navController.navigateUp()
                is ChatsListEffect.NavigateToChat -> navController.navigate(ChatRoute.Chat(effect.chatId))
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ChatsListState.ChatsLoaded -> {
            ChatsListScreen(
                state = state,
                presenter = viewModel
            )
        }

        is ChatsListState.Error -> {
            ErrorScreen(error = state.error)
        }

        ChatsListState.Loading -> {
            LoadingScreen()
        }

        ChatsListState.Idle -> {
            // Ничего не показываем
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatsListScreen(
    state: ChatsListState.ChatsLoaded,
    presenter: ChatsListPresenter
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Чаты",
                        style = PaylanceTheme.typography.titleMedium,
                        color = PaylanceTheme.colors.onBackground
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PaylanceTheme.colors.background
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        if (state.chats.isEmpty()) {
            EmptyChatsScreen()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.chats) { chat ->
                    ChatItem(
                        chat = chat,
                        onClick = { presenter.openChat(chat.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatItem(
    chat: ChatVO,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = PaylanceTheme.colors.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Аватар собеседника
            AsyncImage(
                model = chat.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Информация о чате
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = chat.name,
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onSurface
                )
                VerticalSpacer(4.dp)
                Text(
                    text = chat.message?.text ?: "",
                    style = PaylanceTheme.typography.bodyMedium,
                    color = PaylanceTheme.colors.onSurface.copy(alpha = 0.7f),
                    maxLines = 1
                )
            }

            // Время последнего сообщения
            Text(
                text = chat.message?.time ?: "",
                style = PaylanceTheme.typography.bodySmall,
                color = PaylanceTheme.colors.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun EmptyChatsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "У вас пока нет чатов",
            style = PaylanceTheme.typography.titleMedium,
            color = PaylanceTheme.colors.onBackground
        )
    }
}

@Composable
private fun ErrorScreen(error: ErrorMessage) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error.message,
            style = PaylanceTheme.typography.bodyLarge,
            color = PaylanceTheme.colors.error
        )
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
} 