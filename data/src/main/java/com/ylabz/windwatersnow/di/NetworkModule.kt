package com.ylabz.windwatersnow.di

import com.ylabz.windwatersnow.network.WeatherRepoImpl
import com.ylabz.windwatersnow.network.repo.WeatherRepo
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
    fun bindsWeatherRepo(): WeatherRepo {
        return WeatherRepoImpl()
    }

}