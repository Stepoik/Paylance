package stepan.gorokhov.paylance.features.auth.ui.signup

import stepan.gorokhov.paylance.coreui.validation.ValidationResult

fun SignUpState.validateCredentials(): ValidationResult<SignUpError> {
    if (password.isPasswordSimple()) {
        return ValidationResult.failed(SignUpError.SimplePassword())
    }
    if (email.isEmailNotValid()) {
        return ValidationResult.failed(SignUpError.IncorrectEmail())
    }
    if (password != passwordConfirmation) {
        return ValidationResult.failed(SignUpError.MismatchedPasswords())
    }
    return ValidationResult.success()
}

private fun String.isPasswordSimple(): Boolean {
    return length < 8
}

private fun String.isEmailNotValid(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return !this.matches(emailRegex)
}

private fun String.isVerificationCodeNotValid(): Boolean {
    return length != 6
}