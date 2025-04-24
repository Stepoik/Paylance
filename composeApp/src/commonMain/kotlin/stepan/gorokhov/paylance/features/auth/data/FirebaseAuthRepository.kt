package stepan.gorokhov.paylance.features.auth.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import stepan.gorokhov.paylance.features.auth.domain.AuthRepository
import stepan.gorokhov.paylance.features.auth.domain.models.SignInCredentials
import stepan.gorokhov.paylance.features.auth.domain.models.SignUpCredentials

class FirebaseAuthRepository : AuthRepository {
    private val auth = Firebase.auth

    override suspend fun signIn(credentials: SignInCredentials): Result<Any?> {
        return runCatching {
            auth.signInWithCredential(credentials.toAuthCreds()).user!!
        }
    }

    override suspend fun signUp(credentials: SignUpCredentials): Result<Any?> {
        return runCatching {
            auth.createUserWithEmailAndPassword(email = credentials.login, password = credentials.password).user!!
        }
    }

    override suspend fun logout(): Result<Any?> {
        return runCatching {
            auth.signOut()
        }
    }
}