package com.ylabz.windwatersnow.di

import com.ylabz.windwatersnow.network.WeatherRepoImpl
import com.ylabz.windwatersnow.network.model.OpenWeatherAPI
import com.ylabz.windwatersnow.network.repo.WeatherRepo
import com.ylabz.windwatersnow.network.service.weather.OpenWeatherClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun bindsOpenWeatherAPI(): OpenWeatherAPI {
        return OpenWeatherClient()
    }

    @Singleton
    @Provides
    fun bindsWeatherRepo(
        openWeatherApi: OpenWeatherAPI
    ): WeatherRepo {
        return WeatherRepoImpl(openWeatherApi)
    }

}