package com.ylabz.windwatersnow.network.model.NOAA

data class NOAAMarineWeatherResponse(
    val properties: Properties
)

data class Properties(
    val periods: List<Period>
)

data class Period(
    val name: String,
    val startTime: String,
    val endTime: String,
    val temperature: Int,
    val temperatureUnit: String,
    val windSpeed: String,
    val windDirection: String,
    val shortForecast: String
)