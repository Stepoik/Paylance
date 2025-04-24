package stepan.gorokhov.paylance.features.splash

import stepan.gorokhov.paylance.coreui.viewmodel.UIEffect

sealed class SplashEffect : UIEffect {
    data object NavigateAuth : SplashEffect()
    data object NavigateTests : SplashEffect()
}