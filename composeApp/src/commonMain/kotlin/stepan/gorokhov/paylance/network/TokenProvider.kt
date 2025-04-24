package stepan.gorokhov.paylance.network

interface TokenProvider {
    suspend fun getToken(): String?
}