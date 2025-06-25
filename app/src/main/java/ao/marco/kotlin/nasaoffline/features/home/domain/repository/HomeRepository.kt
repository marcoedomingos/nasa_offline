package ao.marco.kotlin.nasaoffline.features.home.domain.repository

import ao.marco.kotlin.nasaoffline.features.home.domain.entity.PlanetaryApodEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun planetaryApod(): Flow<PlanetaryApodEntity>
}