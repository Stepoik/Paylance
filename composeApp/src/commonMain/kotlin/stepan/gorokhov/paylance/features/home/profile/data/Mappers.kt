package stepan.gorokhov.paylance.features.home.profile.data

import dev.gitlive.firebase.auth.FirebaseUser
import stepan.gorokhov.paylance.features.home.profile.domain.models.User

fun FirebaseUser.toDomain(): User {
    return User(
        name = displayName ?: "",
        image = photoURL ?: ""
    )
}