package stepan.gorokhov.paylance.features.home.profile.domain.models

data class FreelancerInfo(
    val freelancerId: String,
    val imageUrl: String,
    val name: String,
    val description: String,
    val rating: Double,
    val skills: List<String>
)
