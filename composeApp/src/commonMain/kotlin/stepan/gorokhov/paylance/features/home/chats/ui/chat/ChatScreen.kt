package stepan.gorokhov.paylance.features.home.chats.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import org.koin.core.parameter.parametersOf
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.textfields.BaseTextField

@Composable
fun ChatScreen(
    navController: NavController,
    chatId: String
) {
    val viewModel = koinViewModel<ChatViewModel>(parameters = { parametersOf(chatId) })
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.loadChat()
        viewModel.loadMessages()
        viewModel.effect.collect { effect ->
            when (effect) {
                is ChatEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    when (state) {
        is ChatState.ChatLoaded -> {
            ChatScreen(
                state = state,
                presenter = viewModel
            )
        }

        is ChatState.Error -> {
            ErrorScreen(error = state.error)
        }

        ChatState.Loading -> {
            LoadingScreen()
        }

        ChatState.Idle -> {
            // Ничего не показываем
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatScreen(
    state: ChatState.ChatLoaded,
    presenter: ChatPresenter
) {
    var messageText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(state.messages.size) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.size - 1)
        }
    }

    BaseScaffold(
        topBar = {
            ChatTopBar(state = state, presenter = presenter)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.messages) { message ->
                    MessageItem(message = message)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseTextField(
                    value = messageText,
                    onValueChanged = { messageText = it },
                    placeholder = "Введите сообщение",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            presenter.sendMessage(messageText)
                            messageText = ""
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Отправить",
                        tint = PaylanceTheme.colors.primary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopBar(state: ChatState.ChatLoaded, presenter: ChatPresenter) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = state,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = state.chat.title,
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = presenter::navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = PaylanceTheme.colors.onBackground
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PaylanceTheme.colors.background
        )
    )
}

@Composable
private fun MessageItem(message: MessageVO) {
    val isOutgoing = message.isOutgoing
    val backgroundColor = if (isOutgoing) {
        PaylanceTheme.colors.primary
    } else {
        PaylanceTheme.colors.surface
    }
    val textColor = if (isOutgoing) {
        PaylanceTheme.colors.onPrimary
    } else {
        PaylanceTheme.colors.onSurface
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isOutgoing) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isOutgoing) 16.dp else 4.dp,
                        bottomEnd = if (isOutgoing) 4.dp else 16.dp
                    )
                )
                .background(backgroundColor)
                .padding(12.dp),
            horizontalAlignment = if (isOutgoing) Alignment.End else Alignment.Start
        ) {
            Text(
                text = message.text,
                style = PaylanceTheme.typography.bodyMedium,
                color = textColor
            )
            VerticalSpacer(4.dp)
            Text(
                text = message.time,
                style = PaylanceTheme.typography.bodySmall,
                color = textColor.copy(alpha = 0.7f)
            )
        }
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