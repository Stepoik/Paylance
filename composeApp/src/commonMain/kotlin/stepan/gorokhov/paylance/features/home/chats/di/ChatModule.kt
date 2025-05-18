package stepan.gorokhov.paylance.features.home.chats.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.paylance.features.home.chats.data.ChatsRepositoryImpl
import stepan.gorokhov.paylance.features.home.chats.data.network.ChatApi
import stepan.gorokhov.paylance.features.home.chats.domain.ChatsRepository
import stepan.gorokhov.paylance.features.home.chats.ui.chat.ChatViewModel
import stepan.gorokhov.paylance.features.home.chats.ui.list.ChatsListViewModel
import stepan.gorokhov.paylance.network.AUTHORIZED_HTTP_CLIENT

val chatModule = module {
    single { ChatApi(get(AUTHORIZED_HTTP_CLIENT)) }
    single<ChatsRepository> { ChatsRepositoryImpl(get()) }

    viewModel { ChatsListViewModel(get()) }
    viewModel { parameters -> ChatViewModel(parameters.get(), get(), get()) }
}