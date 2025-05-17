package stepan.gorokhov.paylance.features.home.profile.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import stepan.gorokhov.paylance.uikit.components.BaseButton
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.LoadingButton
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.textfields.BaseOutlinedTextField
import stepan.gorokhov.paylance.uikit.images.PhotoCamera

@Composable
fun EditProfileScreen(navController: NavController) {
    val viewModel = koinViewModel<EditProfileViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
        viewModel.effect.collect { effect ->
            when (effect) {
                is EditProfileEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is EditProfileState.ProfileLoaded -> {
            EditProfileScreen(
                state = state,
                presenter = viewModel
            )
        }

        is EditProfileState.Error -> {
            ErrorScreen(error = state.error)
        }

        EditProfileState.Loading -> {
            LoadingScreen()
        }

        EditProfileState.Idle -> {
            // Ничего не показываем
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditProfileScreen(
    state: EditProfileState.ProfileLoaded,
    presenter: EditProfilePresenter
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Редактирование профиля",
                        style = PaylanceTheme.typography.titleMedium,
                        color = PaylanceTheme.colors.onBackground
                    )
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
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Аватар
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(PaylanceTheme.colors.primary.copy(alpha = 0.1f))
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                if (state.profile.avatarUrl != null) {
                    // TODO: Добавить загрузку изображения
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = PaylanceTheme.colors.primary,
                        modifier = Modifier.size(60.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = PaylanceTheme.colors.primary,
                        modifier = Modifier.size(60.dp)
                    )
                }

                // Кнопка изменения аватара
                IconButton(
                    onClick = { /* TODO: Добавить выбор изображения */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(36.dp)
                        .background(
                            color = PaylanceTheme.colors.primary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.PhotoCamera,
                        contentDescription = "Изменить фото",
                        tint = PaylanceTheme.colors.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            VerticalSpacer(24.dp)

            EditProfileForm(state = state, presenter = presenter)
        }
    }
}

@Composable
private fun EditProfileForm(
    state: EditProfileState.ProfileLoaded,
    presenter: EditProfilePresenter
) {
    var isNameError by remember { mutableStateOf(false) }
    BaseOutlinedTextField(
        value = state.profile.name,
        onValueChange = {
            isNameError = false
            presenter.setName(it)
        },
        label = "Имя",
        isError = isNameError,
        supportingText = if (isNameError) "Имя не может быть пустым" else null,
        modifier = Modifier.fillMaxWidth()
    )

    VerticalSpacer(24.dp)

    // Email (только для отображения)
    BaseOutlinedTextField(
        value = state.profile.email,
        onValueChange = { },
        label = "Email",
        enabled = false,
        modifier = Modifier.fillMaxWidth()
    )

    VerticalSpacer(32.dp)

    if (state.isSaving) {
        LoadingButton(Modifier.fillMaxWidth())
    } else {
        BaseButton(
            onClick = {
                if (state.profile.name.isBlank()) {
                    isNameError = true
                    return@BaseButton
                }
                presenter.saveProfile()
            },
            text = "Сохранить",
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving
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