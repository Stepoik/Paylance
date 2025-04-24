package stepan.gorokhov.paylance.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.auth.AuthScheme

fun createAuthorizedHttpClient(tokenProvider: TokenProvider): HttpClient {
    return HttpClient(HttpEngineFactory().createEngine()) {
        commonConfig()
    }.apply {
        enableAuthInterceptor(tokenProvider)
    }
}

private fun HttpClient.enableAuthInterceptor(tokenProvider: TokenProvider) {
    plugin(HttpSend).intercept { request ->
        val token = tokenProvider.getToken() ?: throw IllegalStateException("User not authorized")

        request.headers {
            append(HttpHeaders.Authorization, "${AuthScheme.Bearer} $token")
        }
        execute(request)
    }
}