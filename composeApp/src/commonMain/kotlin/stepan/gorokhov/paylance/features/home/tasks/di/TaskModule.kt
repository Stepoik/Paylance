package stepan.gorokhov.paylance.features.home.tasks.di

import org.koin.dsl.module
import stepan.gorokhov.paylance.features.home.tasks.data.TasksRepositoryImpl
import stepan.gorokhov.paylance.features.home.tasks.data.network.TasksApi
import stepan.gorokhov.paylance.features.home.tasks.domain.TasksRepository
import stepan.gorokhov.paylance.network.AUTHORIZED_HTTP_CLIENT

val taskModule = module {
    single { TasksApi(get(qualifier = AUTHORIZED_HTTP_CLIENT)) }
    single<TasksRepository> { TasksRepositoryImpl(get()) }
}