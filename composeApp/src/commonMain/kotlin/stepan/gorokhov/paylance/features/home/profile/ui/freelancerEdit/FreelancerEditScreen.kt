package stepan.gorokhov.paylance.features.home.profile.ui.freelancerEdit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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

@Composable
fun FreelancerEditScreen(navController: NavController) {
    val viewModel = koinViewModel<FreelancerEditViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
        viewModel.effect.collect { effect ->
            when (effect) {
                is FreelancerEditEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is FreelancerEditState.ProfileLoaded -> {
            FreelancerEditScreen(
                state = state,
                presenter = viewModel
            )
        }

        is FreelancerEditState.Error -> {
            ErrorScreen(error = state.error)
        }

        FreelancerEditState.Loading -> {
            LoadingScreen()
        }

        FreelancerEditState.Idle -> {
            // Ничего не показываем
        }
    }
}

@Composable
private fun FreelancerEditScreen(
    state: FreelancerEditState.ProfileLoaded,
    presenter: FreelancerEditPresenter
) {
    var isDescriptionError by remember { mutableStateOf(false) }

    BaseScaffold(
        topBar = {
            EditFreelanceProfileTopBar(presenter = presenter, modifier = Modifier.fillMaxWidth())
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Описание
            BaseOutlinedTextField(
                value = state.profile.description,
                onValueChange = {
                    presenter.setDescription(it)
                    isDescriptionError = false
                },
                label = "Описание",
                isError = isDescriptionError,
                supportingText = if (isDescriptionError) "Описание не может быть пустым" else null,
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                minLines = 3
            )

            VerticalSpacer(24.dp)
            AddSkillsComponent(state, presenter)
            VerticalSpacer(32.dp)

            if (state.isSaving) {
                LoadingButton(modifier = Modifier.fillMaxWidth())
            } else {
                BaseButton(
                    text = "Сохранить",
                    onClick = {
                        if (state.profile.description.isBlank()) {
                            isDescriptionError = true
                            return@BaseButton
                        }
                        presenter.saveProfile()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isSaving
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditFreelanceProfileTopBar(
    presenter: FreelancerEditPresenter,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                "Редактирование профиля",
                style = PaylanceTheme.typography.titleMedium,
                color = PaylanceTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
        modifier = modifier
    )
}

@Composable
private fun AddSkillsComponent(
    state: FreelancerEditState.ProfileLoaded,
    presenter: FreelancerEditPresenter
) {
    var newSkill by remember { mutableStateOf("") }
    Column {
        // Навыки
        Text(
            text = "Навыки",
            style = PaylanceTheme.typography.titleMedium,
            color = PaylanceTheme.colors.onBackground
        )

        VerticalSpacer(8.dp)

        // Список навыков
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.profile.skills) { skill ->
                AssistChip(
                    onClick = { },
                    label = { Text(skill) },
                    trailingIcon = {
                        IconButton(
                            onClick = { presenter.removeSkill(skill) },
                            modifier = Modifier.size(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Удалить навык",
                                tint = PaylanceTheme.colors.onSurface,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                )
            }
        }

        VerticalSpacer(16.dp)

        // Добавление нового навыка
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BaseOutlinedTextField(
                value = newSkill,
                onValueChange = { newSkill = it },
                label = "Новый навык",
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {
                    if (newSkill.isNotBlank()) {
                        presenter.addSkill(newSkill)
                        newSkill = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить навык",
                    tint = PaylanceTheme.colors.primary
                )
            }
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