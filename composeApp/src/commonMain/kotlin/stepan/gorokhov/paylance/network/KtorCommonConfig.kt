package stepan.gorokhov.paylance.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object KtorConfiguration {
    const val connectTimeoutMillis = 15000L
    const val requestTimeoutMillis = 30000L
}

internal val KtorJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.commonConfig() {
    install(ContentNegotiation) {
        json(KtorJson)
    }

    install(HttpTimeout) {
        connectTimeoutMillis = KtorConfiguration.connectTimeoutMillis
        requestTimeoutMillis = KtorConfiguration.requestTimeoutMillis
    }
}