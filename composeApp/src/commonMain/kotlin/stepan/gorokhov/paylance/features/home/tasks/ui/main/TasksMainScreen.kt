package stepan.gorokhov.paylance.features.home.tasks.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.orders
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.tasks.ui.TasksRoute
import stepan.gorokhov.paylance.uikit.components.BaseScaffold

@Composable
fun TasksMainScreen(navController: NavController) {
    val viewModel = koinViewModel<TasksMainMainViewModel>()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TasksMainEffect.NavigateTask -> navController.navigate("${TasksRoute.Details}/${effect.id}")
                is TasksMainEffect.NavigateProfile -> navController.navigate("${HomeRoute.Profile}")
            }
        }
    }


}

@Composable
fun TasksMainScreen(state: TasksMainState, presenter: TasksMainPresenter) {
    BaseScaffold {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Text(
                    stringResource(Res.string.orders),
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

        }
    }
}