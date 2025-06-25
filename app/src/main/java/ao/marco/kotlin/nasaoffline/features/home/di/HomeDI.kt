package ao.marco.kotlin.nasaoffline.features.home.di

import ao.marco.kotlin.nasaoffline.core.services.api.HomeApi
import ao.marco.kotlin.nasaoffline.features.home.data.data_source.HomeDataSource
import ao.marco.kotlin.nasaoffline.features.home.data.data_source.HomeDataSourceImpl
import ao.marco.kotlin.nasaoffline.features.home.data.repository.HomeRepositoryImpl
import ao.marco.kotlin.nasaoffline.features.home.domain.mapper.PlanetaryApodMapper
import ao.marco.kotlin.nasaoffline.features.home.domain.repository.HomeRepository
import ao.marco.kotlin.nasaoffline.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeDI = module {
    single<HomeApi> {
        get<Retrofit>().create(HomeApi::class.java)
    }

    single<HomeDataSource> {
        HomeDataSourceImpl(get())
    }

    single {
        PlanetaryApodMapper()
    }

    single<HomeRepository> {
        HomeRepositoryImpl(get(), get())
    }

    viewModel {
        HomeViewModel(get())
    }
}
