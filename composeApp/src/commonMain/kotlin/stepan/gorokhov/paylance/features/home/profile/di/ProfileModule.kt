package stepan.gorokhov.paylance.features.home.profile.di

import org.koin.dsl.module
import stepan.gorokhov.paylance.features.home.profile.data.FirebaseUserRepository
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository

val profileModule = module {
    single<UserRepository> { FirebaseUserRepository() }
}