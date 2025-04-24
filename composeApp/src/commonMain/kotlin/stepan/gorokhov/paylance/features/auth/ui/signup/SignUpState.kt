package stepan.gorokhov.paylance.features.auth.ui.signup

import org.jetbrains.compose.resources.StringResource
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.email_incorrect
import paylance.composeapp.generated.resources.incorrect_credentials
import paylance.composeapp.generated.resources.passwords_dont_match
import paylance.composeapp.generated.resources.simple_password
import stepan.gorokhov.paylance.coreui.validation.ValidationError
import stepan.gorokhov.paylance.coreui.viewmodel.UIEffect

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isSinging: Boolean = false,
    val error: SignUpError? = null
)

sealed class SignUpEffect : UIEffect {
    data object NavigateHome : SignUpEffect()
    data object NavigateSignIn : SignUpEffect()
}

sealed class SignUpError(val text: StringResource) : ValidationError {
    class IncorrectEmail : SignUpError(Res.string.email_incorrect)
    class SimplePassword : SignUpError(Res.string.simple_password)
    class MismatchedPasswords : SignUpError(Res.string.passwords_dont_match)
    class IncorrectCredentials : SignUpError(Res.string.incorrect_credentials)
}
