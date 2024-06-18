package com.ylabz.windwatersnow.wind.ui

import com.ylabz.windwatersnow.network.model.WeatherResponse


sealed interface WeatherUiState {
    object Loading : WeatherUiState
    object Error : WeatherUiState
    data class Success(
        val weather : WeatherResponse?,
        val data: List<String> = emptyList(),
        val audioFiles: List<String> = emptyList(),
        val photoFiles: List<String> = emptyList()
    ) : WeatherUiState
    // Else it is a failure ???
}
