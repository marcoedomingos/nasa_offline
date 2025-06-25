package ao.marco.kotlin.nasaoffline.features.home.domain.mapper

import ao.marco.kotlin.nasaoffline.features.home.data.dto.response.PlanetaryApodResponse
import ao.marco.kotlin.nasaoffline.features.home.domain.entity.PlanetaryApodEntity
import ao.marco.kotlin.nasaoffline.helpers.Mapper

class PlanetaryApodMapper : Mapper<PlanetaryApodResponse, PlanetaryApodEntity>() {

    override fun map(data: PlanetaryApodResponse): PlanetaryApodEntity {
        return PlanetaryApodEntity(
            date = data.date,
            explanation = data.explanation,
            hdurl = data.hdurl,
            mediaType = data.mediaType,
            serviceVersion = data.serviceVersion,
            title = data.title,
            url = data.url
        )
    }
}
