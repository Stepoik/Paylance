package stepan.gorokhov.paylance.features.auth.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.paylance.features.auth.data.FirebaseAuthRepository
import stepan.gorokhov.paylance.features.auth.domain.AuthRepository
import stepan.gorokhov.paylance.features.auth.domain.usecases.SignInUseCase
import stepan.gorokhov.paylance.features.auth.domain.usecases.SignUpUseCase
import stepan.gorokhov.paylance.features.auth.ui.signin.SignInViewModel
import stepan.gorokhov.paylance.features.auth.ui.signup.SignUpViewModel

val authModule = module {
    single<AuthRepository> { FirebaseAuthRepository() }
    single { SignUpUseCase(get(), get()) }
    single { SignInUseCase(get(), get()) }

    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
}