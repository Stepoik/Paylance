package stepan.gorokhov.paylance.features.home.tasks.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: String,
    val name: String,
    val imageURL: String
)
