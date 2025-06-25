package ao.marco.kotlin.nasaoffline.features.home.data.data_source

import ao.marco.kotlin.nasaoffline.core.services.api.HomeApi
import ao.marco.kotlin.nasaoffline.features.home.data.dto.response.PlanetaryApodResponse
import retrofit2.Response

class HomeDataSourceImpl(
    private val homeApi: HomeApi
) : HomeDataSource {

    override suspend fun getPlanetaryApod(): Response<PlanetaryApodResponse> {
        try {
            val response: Response<PlanetaryApodResponse> = homeApi.getPlanetaryApod(
                query = mapOf("api_key" to "DEMO_KEY")
            )

            return response
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}
