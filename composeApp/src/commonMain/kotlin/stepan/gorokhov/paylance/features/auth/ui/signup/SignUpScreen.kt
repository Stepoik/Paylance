package stepan.gorokhov.paylance.features.auth.ui.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import stepan.gorokhov.paylance.uikit.components.BaseButton
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.already_registered
import paylance.composeapp.generated.resources.create_account
import paylance.composeapp.generated.resources.email
import paylance.composeapp.generated.resources.password
import paylance.composeapp.generated.resources.password_confirmation
import paylance.composeapp.generated.resources.sign_in
import paylance.composeapp.generated.resources.sign_up
import stepan.gorokhov.paylance.coreui.routing.popUpGraph
import stepan.gorokhov.paylance.features.ApplicationRoute
import stepan.gorokhov.paylance.features.auth.ui.AuthRoute
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.textfields.TextFieldWithIcon
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.images.Lock
import stepan.gorokhov.paylance.uikit.images.Mail

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel = koinViewModel<SignUpViewModel>()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignUpEffect.NavigateSignIn -> navController.navigate(AuthRoute.SignIn)
                is SignUpEffect.NavigateHome -> navController.navigate(ApplicationRoute.Home) {
                    popUpGraph()
                }
            }
        }
    }
    SignUpScreen(state = viewModel.state.collectAsState().value, presenter = viewModel)
}

@Composable
fun SignUpScreen(state: SignUpState, presenter: SignUpPresenter) {
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
                stringResource(Res.string.create_account),
                style = PaylanceTheme.typography.titleMedium,
                color = PaylanceTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
            SignUpForm(
                state = state,
                presenter = presenter,
                modifier = Modifier.padding(top = 40.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Text(
                    stringResource(Res.string.already_registered),
                    color = PaylanceTheme.colors.onBackground,
                    style = PaylanceTheme.typography.bodySmall,
                )
                Text(
                    stringResource(Res.string.sign_in),
                    color = PaylanceTheme.colors.primary,
                    style = PaylanceTheme.typography.bodySmall,
                    modifier = Modifier.clickable { presenter.navigateSignIn() }
                )
            }
        }
    }
}

@Composable
private fun SignUpForm(
    state: SignUpState,
    presenter: SignUpPresenter,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextFieldWithIcon(
            icon = Icons.Default.Person,
            value = state.name,
            placeholder = "Имя",
            onValueChanged = { presenter.setName(it) },
            modifier = Modifier.fillMaxWidth()
        )
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

        TextFieldWithIcon(
            icon = Icons.Lock,
            value = state.passwordConfirmation,
            placeholder = stringResource(Res.string.password_confirmation),
            onValueChanged = { presenter.setPasswordConfirmation(it) },
            modifier = Modifier.fillMaxWidth()
        )

        BaseButton(
            text = stringResource(Res.string.sign_up),
            onClick = { presenter.onSignUpClicked() },
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