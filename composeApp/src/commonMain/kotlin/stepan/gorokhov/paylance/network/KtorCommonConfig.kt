package stepan.gorokhov.paylance.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import stepan.gorokhov.paylance.network.serializers.paylanceSerializersModule
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

internal object KtorConfiguration {
    const val connectTimeoutMillis = 15000L
    const val requestTimeoutMillis = 30000L
}

internal val KtorJson = Json {
    isLenient = true
    ignoreUnknownKeys = true

    serializersModule = paylanceSerializersModule
}

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.commonConfig() {
    install(ContentNegotiation) {
        json(KtorJson)
    }

    install(WebSockets) {
        pingInterval = 15.seconds.toLong(DurationUnit.MILLISECONDS)
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
    }

    install(HttpTimeout) {
        connectTimeoutMillis = KtorConfiguration.connectTimeoutMillis
        requestTimeoutMillis = KtorConfiguration.requestTimeoutMillis
    }
}