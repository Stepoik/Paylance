package stepan.gorokhov.paylance.features.auth.ui.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import stepan.gorokhov.paylance.uikit.components.BaseButton
import kotlinx.coroutines.channels.Channel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.email
import paylance.composeapp.generated.resources.password
import paylance.composeapp.generated.resources.sign_in
import stepan.gorokhov.paylance.coreui.routing.popUpGraph
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.textfields.TextFieldWithIcon
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.images.Lock
import stepan.gorokhov.paylance.uikit.images.Mail

@Composable
fun SignInScreen(navController: NavController) {
    val viewModel = koinViewModel<SignInViewModel>()
    LaunchedEffect(Unit) {
        Channel
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignInEffect.NavigateBack -> navController.navigateUp()
                is SignInEffect.NavigateHome -> navController.navigate(ApplicationRoute.Home) {
                    popUpGraph()
                }
            }
        }
    }
    SignInScreen(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        presenter = viewModel
    )
}

@Composable
fun SignInScreen(state: SignInState, presenter: SignInPresenter) {
    BaseScaffold {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(40.dp)
            Text(
                stringResource(Res.string.sign_in),
                style = PaylanceTheme.typography.titleMedium,
                color = PaylanceTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
            SignInForm(
                state = state,
                presenter = presenter,
                modifier = Modifier.padding(top = 40.dp)
            )
        }
    }
}

@Composable
private fun SignInForm(
    state: SignInState,
    presenter: SignInPresenter,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextFieldWithIcon(
            icon = Icons.Mail,
            value = state.email,
            placeholder = stringResource(Res.string.email),
            onValueChanged = { presenter.setEmail(it) },
            modifier = Modifier.fillMaxWidth()
        )

        TextFieldWithIcon(
            icon = Icons.Lock,
            value = state.password,
            placeholder = stringResource(Res.string.password),
            onValueChanged = { presenter.setPassword(it) },
            modifier = Modifier.fillMaxWidth()
        )

        BaseButton(
            text = stringResource(Res.string.sign_in),
            onClick = { presenter.onSignInClicked() },
            modifier = Modifier.fillMaxWidth()
        )

        if (state.error != null) {
            Text(
                stringResource(state.error.text),
                color = PaylanceTheme.colors.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}