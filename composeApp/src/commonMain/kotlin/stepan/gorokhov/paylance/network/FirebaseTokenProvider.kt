package stepan.gorokhov.paylance.network

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class FirebaseTokenProvider : TokenProvider {
    private val auth = Firebase.auth

    override suspend fun getToken(): String? {
        return auth.currentUser?.getIdToken(false)
    }
}