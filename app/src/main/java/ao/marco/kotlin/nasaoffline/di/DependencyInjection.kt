package ao.marco.kotlin.nasaoffline.di

import ao.marco.kotlin.nasaoffline.BuildConfig
import ao.marco.kotlin.nasaoffline.features.home.di.homeDI
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    loadKoinModules(listOf(homeDI))
}
