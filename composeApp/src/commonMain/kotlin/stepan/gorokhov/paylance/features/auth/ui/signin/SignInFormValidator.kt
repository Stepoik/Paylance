package stepan.gorokhov.paylance.features.auth.ui.signin

import stepan.gorokhov.paylance.coreui.validation.ValidationResult


fun SignInState.validateCredentials(): ValidationResult<SignInError> {
    if (email.isEmailNotValid()) {
        return ValidationResult.failed(SignInError.IncorrectEmail())
    }
    return ValidationResult.success()
}

private fun String.isEmailNotValid(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return !this.matches(emailRegex)
}