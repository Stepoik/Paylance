package stepan.gorokhov.paylance.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import stepan.gorokhov.paylance.features.auth.di.authModule
import stepan.gorokhov.paylance.features.home.profile.di.profileModule
import stepan.gorokhov.paylance.features.home.projects.di.projectModule
import stepan.gorokhov.paylance.features.splash.splashModule
import stepan.gorokhov.paylance.network.networkModule

object KoinSDK {
    fun init(platformModule: Module) {
        startKoin {
            modules(
                platformModule,
                authModule,
                profileModule,
                splashModule,
                networkModule,
                projectModule
            )
        }
    }
}