package com.ylabz.windwatersnow.network

import com.ylabz.windwatersnow.network.model.OpenWeatherAPI
import com.ylabz.windwatersnow.network.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    // private val topicDao: TopicDao,
    private val network: OpenWeatherAPI
) : WeatherRepo {


    override suspend fun getWeather(location: String): String {
        return network.getWeather(location)
    }


}