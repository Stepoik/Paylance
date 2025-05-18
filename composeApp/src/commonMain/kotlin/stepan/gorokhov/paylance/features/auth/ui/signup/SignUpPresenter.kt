package stepan.gorokhov.paylance.features.auth.ui.signup

interface SignUpPresenter {
    fun setEmail(email: String)

    fun setPassword(password: String)

    fun setPasswordConfirmation(password: String)

    fun onSignUpClicked()

    fun navigateSignIn()

    fun setName(name: String)
}