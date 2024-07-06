package com.ylabz.windwatersnow.wind.ui

import com.ylabz.windwatersnow.network.model.OpenWeatherResponse


sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    data class Success(
        val weather : OpenWeatherResponse?,
        var location: String = "Santa Barbara, US",
        val data: List<String> = emptyList(),
        val audioFiles: List<String> = emptyList(),
        val photoFiles: List<String> = emptyList()
    ) : WeatherUiState
    // Else it is a failure ???
}
