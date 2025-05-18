package stepan.gorokhov.paylance.features.home

import androidx.lifecycle.ViewModel
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val isAuthorized = userRepository.isAuthorized
}