package stepan.gorokhov.paylance.features.auth.ui.signin

import org.jetbrains.compose.resources.StringResource
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.email_incorrect
import paylance.composeapp.generated.resources.incorrect_credentials
import stepan.gorokhov.paylance.coreui.validation.ValidationError
import stepan.gorokhov.paylance.coreui.viewmodel.UIEffect

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isSinging: Boolean = false,
    val error: SignInError? = null
)

sealed class SignInEffect : UIEffect {
    data object NavigateBack : SignInEffect()
    data object NavigateHome : SignInEffect()
}

sealed class SignInError(val text: StringResource) : ValidationError {
    class IncorrectCredentials : SignInError(Res.string.incorrect_credentials)
    class IncorrectEmail : SignInError(Res.string.email_incorrect)
}
