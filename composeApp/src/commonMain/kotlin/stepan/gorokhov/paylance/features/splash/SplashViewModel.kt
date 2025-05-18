package stepan.gorokhov.paylance.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository

class SplashViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _effect = MutableSharedFlow<SplashEffect>()
    val effect = _effect.asSharedFlow()

    fun checkUserAuth() {
        viewModelScope.launch {
            userRepository.refreshUser()
            if (userRepository.isAuthorized.first()) {
                _effect.emit(SplashEffect.NavigateTests)
            } else {
                _effect.emit(SplashEffect.NavigateAuth)
            }
        }
    }
}