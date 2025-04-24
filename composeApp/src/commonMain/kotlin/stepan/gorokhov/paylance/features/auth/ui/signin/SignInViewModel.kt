package stepan.gorokhov.paylance.features.auth.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.validation.getErrorOrNull
import stepan.gorokhov.paylance.features.auth.domain.models.SignInCredentials
import stepan.gorokhov.paylance.features.auth.domain.usecases.SignInUseCase

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel(), SignInPresenter {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SignInEffect>()
    val effect = _effect.asSharedFlow()

    override fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun setPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onSignInClicked() {
        val state = _state.value
        if (state.isSinging) return

        val validationResult = state.validateCredentials()
        validationResult.getErrorOrNull()?.let { error ->
            _state.update { it.copy(error = error) }
            return
        }
        viewModelScope.launch {
            signInUseCase.execute(state.toSignInCredentials()).onFailure {
                _state.update { it.copy(error = SignInError.IncorrectCredentials()) }
            }.onSuccess {
                _effect.emit(SignInEffect.NavigateHome)
            }
        }
    }

    override fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(SignInEffect.NavigateBack)
        }
    }
}

private fun SignInState.toSignInCredentials() = SignInCredentials(
    login = email,
    password = password
)