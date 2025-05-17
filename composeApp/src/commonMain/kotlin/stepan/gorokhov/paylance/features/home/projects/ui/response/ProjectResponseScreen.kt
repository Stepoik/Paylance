package stepan.gorokhov.paylance.features.home.projects.ui.response

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.LoadingButton
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.spacer

@Composable
fun ProjectResponseScreen(navController: NavController, responseId: String) {
    val viewModel =
        koinViewModel<ProjectResponseViewModel>(parameters = { parametersOf(responseId) })

    LaunchedEffect(Unit) {
        viewModel.loadResponse()
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProjectResponseEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ProjectResponseState.ResponseLoaded -> {
            ProjectResponseScreen(
                state = state,
                presenter = viewModel
            )
        }

        is ProjectResponseState.Error -> {
            ErrorScreen(error = state.error)
        }

        ProjectResponseState.Loading -> {
            LoadingScreen()
        }

        ProjectResponseState.Idle -> {
            // Ничего не показываем
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProjectResponseScreen(
    state: ProjectResponseState.ResponseLoaded,
    presenter: ProjectResponsePresenter
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Заявка",
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
            freelancerInfo(state.response)
            spacer(24.dp)
            actionButtons(
                isAccepting = state.isAccepting,
                isRejecting = state.isRejecting,
                presenter = presenter
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
fun LazyListScope.freelancerInfo(response: ProjectResponseVO) {
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
                    // Аватар фрилансера
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(PaylanceTheme.colors.primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = PaylanceTheme.colors.primary,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = response.freelancerName,
                            style = PaylanceTheme.typography.titleMedium,
                            color = PaylanceTheme.colors.onBackground
                        )
                        VerticalSpacer(4.dp)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = PaylanceTheme.colors.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = response.freelancerRating,
                                style = PaylanceTheme.typography.bodyMedium,
                                color = PaylanceTheme.colors.onBackground.copy(alpha = 0.7f)
                            )
                        }
                    }
                }

                VerticalSpacer(16.dp)

                // Навыки фрилансера
                Text(
                    text = "Навыки",
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground
                )
                VerticalSpacer(8.dp)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    response.freelancerSkills.forEach { skill ->
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    text = skill,
                                    style = PaylanceTheme.typography.bodyMedium
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = PaylanceTheme.colors.primary.copy(alpha = 0.1f),
                                labelColor = PaylanceTheme.colors.primary
                            )
                        )
                    }
                }
            }
        }
    }
}

fun LazyListScope.actionButtons(
    isAccepting: Boolean,
    isRejecting: Boolean,
    presenter: ProjectResponsePresenter
) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isAccepting) {
                LoadingButton(modifier = Modifier.weight(1f))
            } else {
                Button(
                    onClick = presenter::acceptResponse,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PaylanceTheme.colors.primary
                    )
                ) {
                    Text("Принять")
                }
            }

            if (isRejecting) {
                LoadingButton(modifier = Modifier.weight(1f))
            } else {
                OutlinedButton(
                    onClick = presenter::rejectResponse,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Отклонить")
                }
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

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
} 