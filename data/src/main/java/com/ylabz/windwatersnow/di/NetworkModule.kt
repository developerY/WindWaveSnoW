package com.ylabz.windwatersnow.di

import com.ylabz.windwatersnow.network.WeatherRepoImpl
import com.ylabz.windwatersnow.network.model.WeatherService
import com.ylabz.windwatersnow.network.repo.WeatherRepo
import com.ylabz.windwatersnow.network.service.weather.RetrofitClient.weatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun bindsNetwork(
    ): WeatherService {
        return weatherService
    }


    @Singleton
    @Provides
    fun bindsWeatherRepo(
        weatherService: WeatherService
    ): WeatherRepo {
        return WeatherRepoImpl(weatherService)
    }

}