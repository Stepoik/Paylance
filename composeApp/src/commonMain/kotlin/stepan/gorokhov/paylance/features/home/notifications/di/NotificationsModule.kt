package stepan.gorokhov.paylance.features.home.notifications.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.paylance.features.home.notifications.data.NotificationRepositoryImpl
import stepan.gorokhov.paylance.features.home.notifications.data.network.NotificationApi
import stepan.gorokhov.paylance.features.home.notifications.domain.NotificationsRepository
import stepan.gorokhov.paylance.features.home.notifications.ui.NotificationsViewModel
import stepan.gorokhov.paylance.network.AUTHORIZED_HTTP_CLIENT

val notificationModule = module {
    single { NotificationApi(get(qualifier = AUTHORIZED_HTTP_CLIENT)) }
    single<NotificationsRepository> { NotificationRepositoryImpl(get()) }

    viewModel { NotificationsViewModel(get()) }
}