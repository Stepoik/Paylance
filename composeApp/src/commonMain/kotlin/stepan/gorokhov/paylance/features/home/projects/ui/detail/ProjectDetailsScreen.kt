package stepan.gorokhov.paylance.features.home.projects.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import stepan.gorokhov.paylance.uikit.components.BaseButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.common.LoadingScreen
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectStatus
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.LoadingButton
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.spacer
import stepan.gorokhov.paylance.uikit.images.Event
import stepan.gorokhov.paylance.uikit.images.Payment
import stepan.gorokhov.paylance.uikit.images.Work

@Composable
fun ProjectDetailsScreen(navController: NavController, projectId: String) {
    val viewModel = koinViewModel<ProjectDetailsViewModel>(parameters = { parametersOf(projectId) })
    LaunchedEffect(Unit) {
        viewModel.loadProject()
        viewModel.effect.collect {
            when (it) {
                is ProjectDetailsEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }
    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ProjectDetailsState.ProjectLoaded -> {
            ProjectDetailsScreen(
                presenter = viewModel,
                state = state
            )
        }

        is ProjectDetailsState.Error -> {
            ErrorScreen(error = state.error)
        }

        ProjectDetailsState.Loading -> {
            LoadingScreen()
        }

        ProjectDetailsState.Idle -> {
            // Пустой экран
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailsScreen(
    presenter: ProjectDetailsPresenter,
    state: ProjectDetailsState.ProjectLoaded,
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Проект",
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            spacer(16.dp)
            projectHeader(state.project)
            spacer(24.dp)
            projectDescription(state.project)
            spacer(24.dp)
            projectInfo(state.project)
            spacer(24.dp)
            skillsSection(state.project.skills)
            spacer(24.dp)
            actionButtons(
                state = state,
                isResponding = state.isResponding,
                isRespond = state.project.isRespond,
                presenter = presenter
            )
        }
    }
}

fun LazyListScope.projectHeader(project: ProjectVO) {
    item {
        Column {
            Text(
                text = project.title,
                style = PaylanceTheme.typography.titleLarge,
                color = PaylanceTheme.colors.onBackground
            )
            VerticalSpacer(8.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = PaylanceTheme.colors.primary
                )
                Text(
                    text = project.clientName,
                    style = PaylanceTheme.typography.bodyLarge,
                    color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}

fun LazyListScope.projectDescription(project: ProjectVO) {
    item {
        Column {
            Text(
                text = "Описание",
                style = PaylanceTheme.typography.titleMedium,
                color = PaylanceTheme.colors.onBackground
            )
            VerticalSpacer(12.dp)
            Text(
                text = project.description,
                style = PaylanceTheme.typography.bodyLarge,
                color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

fun LazyListScope.projectInfo(project: ProjectVO) {
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
                InfoRow(
                    icon = Icons.Payment,
                    title = "Бюджет",
                    value = project.budget
                )
                VerticalSpacer(16.dp)
                InfoRow(
                    icon = Icons.Event,
                    title = "Сроки",
                    value = project.deadline
                )
                VerticalSpacer(16.dp)
                InfoRow(
                    icon = Icons.Work,
                    title = "Тип работы",
                    value = project.workType
                )
            }
        }
    }
}

@Composable
private fun InfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PaylanceTheme.colors.primary
        )
        Column {
            Text(
                text = title,
                style = PaylanceTheme.typography.bodyMedium,
                color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
            )
            Text(
                text = value,
                style = PaylanceTheme.typography.bodyLarge,
                color = PaylanceTheme.colors.onBackground
            )
        }
    }
}

fun LazyListScope.skillsSection(skills: List<String>) {
    item {
        Text(
            text = "Требуемые навыки",
            style = PaylanceTheme.typography.titleMedium,
            color = PaylanceTheme.colors.onBackground
        )
        VerticalSpacer(12.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                Chip(
                    text = skill,
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@Composable
private fun Chip(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = PaylanceTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = PaylanceTheme.typography.bodyMedium,
            color = PaylanceTheme.colors.primary
        )
    }
}

fun LazyListScope.actionButtons(
    state: ProjectDetailsState.ProjectLoaded,
    isResponding: Boolean,
    isRespond: Boolean,
    presenter: ProjectDetailsPresenter
) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.project.isOwner) {
                BaseButton(
                    "Закрыть проект",
                    onClick = presenter::closeProject,
                    modifier = Modifier.weight(1f)
                )
            } else {
                if (state.project.status == ProjectStatus.CANCELLED) {
                    BaseButton(
                        "Проект закрыт",
                        onClick = {},
                        enabled = false,
                        modifier = Modifier.weight(1f)
                    )
                } else if (isResponding) {
                    LoadingButton(modifier = Modifier.weight(1f))
                } else if (isRespond) {
                    BaseButton(
                        "Заявка уже оставлена",
                        onClick = {},
                        enabled = false,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    BaseButton(
                        "Оставить заявку",
                        onClick = presenter::responseOnProject,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            IconButton(
                onClick = { /* TODO: Сохранить в избранное */ },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PaylanceTheme.colors.primary.copy(alpha = 0.1f))
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "В избранное",
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