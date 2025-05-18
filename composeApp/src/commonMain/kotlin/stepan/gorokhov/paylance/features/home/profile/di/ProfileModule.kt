package stepan.gorokhov.paylance.features.home.profile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.paylance.features.home.profile.data.FirebaseUserRepository
import stepan.gorokhov.paylance.features.home.profile.data.network.UserApi
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.profile.ui.edit.EditProfileViewModel
import stepan.gorokhov.paylance.features.home.profile.ui.freelancerEdit.FreelancerEditViewModel
import stepan.gorokhov.paylance.features.home.profile.ui.main.ProfileViewModel
import stepan.gorokhov.paylance.network.AUTHORIZED_HTTP_CLIENT

val profileModule = module {
    single { UserApi(get(AUTHORIZED_HTTP_CLIENT)) }
    single<UserRepository> { FirebaseUserRepository(get()) }

    viewModel { ProfileViewModel(get(), get()) }
    viewModel { EditProfileViewModel(get()) }
    viewModel { FreelancerEditViewModel(get()) }
}