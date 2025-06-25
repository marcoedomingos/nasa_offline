package ao.marco.kotlin.nasaoffline.features.home.data.data_source

import ao.marco.kotlin.nasaoffline.features.home.data.dto.response.PlanetaryApodResponse
import retrofit2.Response

interface HomeDataSource {
    suspend fun getPlanetaryApod(): Response<PlanetaryApodResponse>
}