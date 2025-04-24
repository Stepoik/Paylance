package stepan.gorokhov.paylance.features.auth.data

import dev.gitlive.firebase.auth.EmailAuthProvider
import stepan.gorokhov.paylance.features.auth.domain.models.SignInCredentials

fun SignInCredentials.toAuthCreds() =
    EmailAuthProvider.credential(email = login, password = password)