package stepan.gorokhov.paylance.features.auth.ui.signin

import androidx.compose.runtime.Stable

@Stable
interface SignInPresenter {
    fun setEmail(email: String)

    fun setPassword(password: String)

    fun onSignInClicked()

    fun navigateBack()
}