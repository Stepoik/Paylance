package stepan.gorokhov.paylance.features.home.projects.ui.generateDescription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.common.LoadingScreen
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsRoute
import stepan.gorokhov.paylance.uikit.components.BaseButton
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.textfields.BaseOutlinedTextField

@Composable
fun GenerateDescriptionScreen(navController: NavController) {
    val viewModel = koinViewModel<GenerateDescriptionViewModel>()
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is GenerateDescriptionEffect.NavigateBack -> navController.navigateUp()
                is GenerateDescriptionEffect.NavigateToCreateProject -> {
                    navController.navigate(
                        MyProjectsRoute.CreateProject(
                            title = it.title,
                            description = it.description
                        )
                    )
                }
            }
        }
    }
    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is GenerateDescriptionState.DescriptionGenerated -> {
            GenerateDescriptionScreen(
                presenter = viewModel,
                state = state
            )
        }

        is GenerateDescriptionState.Error -> {
            ErrorScreen(error = state.error)
        }

        GenerateDescriptionState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun GenerateDescriptionScreen(
    presenter: GenerateDescriptionPresenter,
    state: GenerateDescriptionState.DescriptionGenerated
) {
    BaseScaffold(
        topBar = {
            GenerateDescriptionTopBar(presenter)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.title == null || state.description == null) {
                BaseOutlinedTextField(
                    value = state.prompt,
                    onValueChange = presenter::setPrompt,
                    label = "Опишите ваш проект",
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
                BaseButton(
                    text = "Сгенерировать",
                    onClick = presenter::generateDescription,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                GeneratedDescriptionApproval(
                    title = state.title,
                    description = state.description,
                    presenter = presenter
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenerateDescriptionTopBar(presenter: GenerateDescriptionPresenter) {
    TopAppBar(
        title = {
            Text(
                "Генерация описания",
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

@Composable
private fun GeneratedDescriptionApproval(
    title: String,
    description: String,
    presenter: GenerateDescriptionPresenter
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Название",
            style = PaylanceTheme.typography.titleMedium,
            color = PaylanceTheme.colors.onBackground
        )
        Text(
            text = title,
            style = PaylanceTheme.typography.bodyLarge,
            color = PaylanceTheme.colors.onBackground
        )
        VerticalSpacer(8.dp)
        Text(
            text = "Описание",
            style = PaylanceTheme.typography.titleMedium,
            color = PaylanceTheme.colors.onBackground
        )
        Text(
            text = description,
            style = PaylanceTheme.typography.bodyLarge,
            color = PaylanceTheme.colors.onBackground
        )
        VerticalSpacer(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BaseButton(
                text = "Отклонить",
                onClick = presenter::rejectDescription,
                modifier = Modifier.weight(1f)
            )
            BaseButton(
                text = "Принять",
                onClick = presenter::acceptDescription,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ErrorScreen(error: ErrorMessage) {
    BaseScaffold {
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
} 