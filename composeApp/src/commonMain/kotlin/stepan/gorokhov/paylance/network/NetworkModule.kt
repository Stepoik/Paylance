package stepan.gorokhov.paylance.network

import org.koin.core.qualifier.named
import org.koin.dsl.module

val AUTHORIZED_HTTP_CLIENT = named("authorized_http_client")

val networkModule = module {
    single<TokenProvider> { FirebaseTokenProvider() }
    single(AUTHORIZED_HTTP_CLIENT) { createAuthorizedHttpClient(get()) }
}