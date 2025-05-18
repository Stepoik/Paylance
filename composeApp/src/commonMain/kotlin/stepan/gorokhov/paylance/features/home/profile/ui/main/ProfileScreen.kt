package stepan.gorokhov.paylance.features.home.profile.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.profile.ui.ProfileRoute
import stepan.gorokhov.paylance.uikit.components.BaseButton
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.spacer
import stepan.gorokhov.paylance.uikit.images.Work

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel = koinViewModel<ProfileViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProfileEffect.NavigateToEditProfile -> {
                    navController.navigate(ProfileRoute.Edit)
                }

                is ProfileEffect.NavigateToEditFreelancerProfile -> {
                    navController.navigate(ProfileRoute.EditFreelancer)
                }
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ProfileState.ProfileLoaded -> {
            ProfileScreen(
                state = state,
                presenter = viewModel
            )
        }

        is ProfileState.Error -> {
            ErrorScreen(error = state.error)
        }

        ProfileState.Loading -> {
            LoadingScreen()
        }

        ProfileState.Idle -> {
            // Ничего не показываем
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(
    state: ProfileState.ProfileLoaded,
    presenter: ProfilePresenter
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Профиль",
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            spacer(16.dp)
            userInfo(state.profile)
            spacer(24.dp)
            editButtons(presenter)
            spacer(12.dp)
            logoutButton(presenter)
        }
    }
}

fun LazyListScope.userInfo(profile: ProfileVO) {
    item {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = PaylanceTheme.colors.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Аватар пользователя
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(PaylanceTheme.colors.primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (profile.avatarUrl != null) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = PaylanceTheme.colors.primary,
                                modifier = Modifier.size(40.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = PaylanceTheme.colors.primary,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = profile.name,
                            style = PaylanceTheme.typography.titleLarge,
                            color = PaylanceTheme.colors.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        VerticalSpacer(4.dp)
                        Text(
                            text = profile.email,
                            style = PaylanceTheme.typography.bodyMedium,
                            color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
    }
}

fun LazyListScope.editButtons(presenter: ProfilePresenter) {
    item {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = presenter::navigateToEditProfile,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = PaylanceTheme.colors.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Редактировать профиль", color = PaylanceTheme.colors.primary)
            }

            OutlinedButton(
                onClick = presenter::navigateToEditFreelancerProfile,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Work,
                    contentDescription = null,
                    tint = PaylanceTheme.colors.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Редактировать профиль фрилансера", color = PaylanceTheme.colors.primary)
            }
        }
    }
}

fun LazyListScope.logoutButton(presenter: ProfilePresenter) {
    item {
        BaseButton(text = "Выйти", onClick = presenter::logout, modifier = Modifier.fillMaxWidth())
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