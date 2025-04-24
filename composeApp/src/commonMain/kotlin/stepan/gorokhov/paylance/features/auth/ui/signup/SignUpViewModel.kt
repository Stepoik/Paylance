package stepan.gorokhov.paylance.features.auth.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.validation.getErrorOrNull
import stepan.gorokhov.paylance.features.auth.domain.models.SignUpCredentials
import stepan.gorokhov.paylance.features.auth.domain.usecases.SignUpUseCase

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel(), SignUpPresenter {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SignUpEffect>()
    val effect = _effect.asSharedFlow()

    override fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun setPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun setPasswordConfirmation(password: String) {
        _state.update { it.copy(passwordConfirmation = password) }
    }

    override fun onSignUpClicked() {
        val state = _state.value
        if (state.isSinging) return

        val validationResult = state.validateCredentials()
        validationResult.getErrorOrNull()?.let { error ->
            _state.update { it.copy(error = error) }
            return
        }
        viewModelScope.launch {
            signUpUseCase.execute(state.toSignUpCredentials()).onFailure {
                _state.update { it.copy(error = SignUpError.IncorrectCredentials()) }
            }.onSuccess {
                _effect.emit(SignUpEffect.NavigateHome)
            }
        }
    }

    override fun navigateSignIn() {
        viewModelScope.launch {
            _effect.emit(SignUpEffect.NavigateSignIn)
        }
    }
}

private fun SignUpState.toSignUpCredentials(): SignUpCredentials {
    return SignUpCredentials(
        login = email,
        password = password
    )
}