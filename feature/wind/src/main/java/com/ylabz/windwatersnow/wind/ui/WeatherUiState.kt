package com.ylabz.windwatersnow.wind.ui

import com.ylabz.windwatersnow.network.model.WeatherResponse


sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    data class Success(
        val weather : WeatherResponse?,
        var location: String = "Santa Barbara County, US",
        val data: List<String> = emptyList(),
        val audioFiles: List<String> = emptyList(),
        val photoFiles: List<String> = emptyList()
    ) : WeatherUiState
    // Else it is a failure ???
}
