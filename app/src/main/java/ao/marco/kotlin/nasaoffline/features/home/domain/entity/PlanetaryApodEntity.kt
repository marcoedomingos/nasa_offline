package ao.marco.kotlin.nasaoffline.features.home.domain.entity

data class PlanetaryApodEntity(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String
)
