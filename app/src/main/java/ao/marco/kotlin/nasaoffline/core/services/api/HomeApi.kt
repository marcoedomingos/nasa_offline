package ao.marco.kotlin.nasaoffline.core.services.api

import ao.marco.kotlin.nasaoffline.features.home.data.dto.response.PlanetaryApodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HomeApi {
    @GET("planetary/apod")
    suspend fun getPlanetaryApod(
        @QueryMap query: Map<String, String>
    ): Response<PlanetaryApodResponse>
}