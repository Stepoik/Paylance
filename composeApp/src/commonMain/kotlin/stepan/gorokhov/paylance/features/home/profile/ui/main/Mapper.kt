package stepan.gorokhov.paylance.features.home.profile.ui.main

import stepan.gorokhov.paylance.features.home.profile.domain.models.User

fun User.toVO(): ProfileVO {
    return ProfileVO(
        id = id,
        name = name,
        email = email,
        avatarUrl = image
    )
}