package stepan.gorokhov.paylance.features.home.notifications.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.common.LoadingScreen
import stepan.gorokhov.paylance.features.home.notifications.NotificationRoute
import stepan.gorokhov.paylance.features.home.notifications.domain.models.NotificationType
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.images.NotificationsOff

@Composable
fun NotificationsScreen(navController: NavController) {
    val viewModel = koinViewModel<NotificationsViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadNotifications()
        viewModel.effect.collect { effect ->
            when (effect) {
                is NotificationsEffect.NavigateBack -> navController.navigateUp()
                is NotificationsEffect.NavigateToProject ->
                    navController.navigate(NotificationRoute.ProjectDetails(effect.projectId))

                is NotificationsEffect.NavigateToResponse ->
                    navController.navigate(NotificationRoute.ResponseDetails(effect.responseId))
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is NotificationsState.NotificationsLoaded -> {
            NotificationsScreen(
                state = state,
                presenter = viewModel
            )
        }

        is NotificationsState.Error -> {
            ErrorScreen(error = state.error)
        }

        NotificationsState.Loading -> {
            LoadingScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationsScreen(
    state: NotificationsState.NotificationsLoaded,
    presenter: NotificationsPresenter
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Уведомления",
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
        val pullToRefreshState = rememberPullToRefreshState()
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            state = pullToRefreshState,
            onRefresh = {
                presenter.loadNotifications(true)
            },
            indicator = {
                RefreshIndicator(isRefreshing = state.isLoading, state = pullToRefreshState)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (state.notifications.isEmpty()) {
                    item {
                        EmptyNotifications()
                    }
                } else {
                    items(state.notifications) { notification ->
                        NotificationItem(
                            notification = notification,
                            onClick = { presenter.onNotificationClick(notification) }
                        )
                        VerticalSpacer(8.dp)
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationItem(
    notification: NotificationVO,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PaylanceTheme.colors.surface
        ),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка уведомления
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        when (notification.type) {
                            NotificationType.PROJECT_RESPONSE -> PaylanceTheme.colors.primary.copy(
                                alpha = 0.1f
                            )

                            NotificationType.RESPONSE_RESULT -> PaylanceTheme.colors.secondary.copy(
                                alpha = 0.1f
                            )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (notification.type) {
                        NotificationType.PROJECT_RESPONSE -> Icons.Default.Person
                        NotificationType.RESPONSE_RESULT -> Icons.Default.CheckCircle
                    },
                    contentDescription = null,
                    tint = when (notification.type) {
                        NotificationType.PROJECT_RESPONSE -> PaylanceTheme.colors.primary
                        NotificationType.RESPONSE_RESULT -> PaylanceTheme.colors.secondary
                    }
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Текст уведомления
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notification.title,
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground
                )
                VerticalSpacer(4.dp)
                Text(
                    text = notification.message,
                    style = PaylanceTheme.typography.bodyMedium,
                    color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
                )
                VerticalSpacer(4.dp)
                Text(
                    text = notification.time,
                    style = PaylanceTheme.typography.bodySmall,
                    color = PaylanceTheme.colors.onBackground.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
private fun EmptyNotifications() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.NotificationsOff,
                contentDescription = null,
                tint = PaylanceTheme.colors.onBackground.copy(alpha = 0.5f),
                modifier = Modifier.size(48.dp)
            )
            VerticalSpacer(16.dp)
            Text(
                text = "Нет уведомлений",
                style = PaylanceTheme.typography.titleMedium,
                color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BoxScope.RefreshIndicator(isRefreshing: Boolean, state: PullToRefreshState) {
    Indicator(
        modifier = Modifier.align(Alignment.TopCenter),
        isRefreshing = isRefreshing,
        state = state,
        containerColor = PaylanceTheme.colors.surface,
        color = PaylanceTheme.colors.primary
    )
}