package com.ylabz.windwatersnow.network.model.NOAA

data class NOAAWeatherDataResponse(
    val results: List<NOAAWeatherResult>
)

data class NOAAWeatherResult(
    val date: String,
    val datatype: String,
    val station: String,
    val attributes: String,
    val value: Double
)