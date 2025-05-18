package stepan.gorokhov.paylance.features.auth.domain.models

data class SignUpCredentials(
    val name: String,
    val login: String,
    val password: String
)
