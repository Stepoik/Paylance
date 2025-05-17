package stepan.gorokhov.paylance.features.home.profile.ui.edit

import stepan.gorokhov.paylance.features.home.profile.domain.models.User

fun User.toEditProfileVO() = EditProfileVO(
    id = id,
    name = name,
    email = email,
    avatarUrl = image
)

fun EditProfileVO.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        image = avatarUrl ?: ""
    )
}