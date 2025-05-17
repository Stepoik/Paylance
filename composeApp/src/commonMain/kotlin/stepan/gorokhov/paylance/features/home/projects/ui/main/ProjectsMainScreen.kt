package stepan.gorokhov.paylance.features.home.projects.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.orders
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.projects.ui.ProjectsRoute
import stepan.gorokhov.paylance.features.home.projects.ui.common.projects
import stepan.gorokhov.paylance.features.home.projects.ui.common.projectListSkeleton
import stepan.gorokhov.paylance.features.home.projects.ui.main.components.searchButton
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.HorizontalSpacer
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.spacer

@Composable
fun ProjectsMainScreen(navController: NavController) {
    val viewModel = koinViewModel<ProjectsMainViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadProjects()
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProjectsMainEffect.NavigateProject -> navController.navigate(ProjectsRoute.Details(effect.id))
                is ProjectsMainEffect.NavigateProfile -> navController.navigate("${HomeRoute.Profile}")
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ProjectsMainState.ProjectsLoaded -> {
            ProjectsMainScreen(state = state, presenter = viewModel)
        }
        is ProjectsMainState.Error -> {
            ErrorScreen(
                error = state.error,
                onRetry = viewModel::loadProjects
            )
        }
        is ProjectsMainState.Loading -> {
            ProjectsMainSkeleton()
        }
    }
}

@Composable
private fun ErrorScreen(
    error: ErrorMessage,
    onRetry: () -> Unit
) {
    BaseScaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = error.message,
                    style = PaylanceTheme.typography.bodyLarge,
                    color = PaylanceTheme.colors.error
                )
                VerticalSpacer(16.dp)
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PaylanceTheme.colors.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    HorizontalSpacer(8.dp)
                    Text(
                        text = "Повторить",
                        style = PaylanceTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectsMainSkeleton() {
    BaseScaffold {
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            spacer(20.dp)
            item {
                Text(
                    stringResource(Res.string.orders),
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            spacer(20.dp)
            searchButton { }
            projectListSkeleton()
        }
    }
}

@Composable
fun ProjectsMainScreen(state: ProjectsMainState.ProjectsLoaded, presenter: ProjectsMainPresenter) {
    BaseScaffold {
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            spacer(20.dp)
            item {
                Text(
                    stringResource(Res.string.orders),
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            spacer(20.dp)
            searchButton { }
            projects(state.projects, onProjectClick = presenter::onProjectClicked)
        }
    }
}