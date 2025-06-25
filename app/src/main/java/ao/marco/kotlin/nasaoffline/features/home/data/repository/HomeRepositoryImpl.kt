package ao.marco.kotlin.nasaoffline.features.home.data.repository

import ao.marco.kotlin.nasaoffline.features.home.data.data_source.HomeDataSource
import ao.marco.kotlin.nasaoffline.features.home.domain.entity.PlanetaryApodEntity
import ao.marco.kotlin.nasaoffline.features.home.domain.mapper.PlanetaryApodMapper
import ao.marco.kotlin.nasaoffline.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource,
    private val planetaryApodMapper: PlanetaryApodMapper
) : HomeRepository {

    override suspend fun planetaryApod(): Flow<PlanetaryApodEntity> = flow {
        val response = homeDataSource.getPlanetaryApod()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(planetaryApodMapper.map(body))
            } else {
                throw Exception("The response is empty.")
            }
        } else {
            throw Exception("HTTP Error ${response.code()}: ${response.message()}")
        }
    }
}
