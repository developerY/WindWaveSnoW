package com.ylabz.windwatersnow.wind.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.network.repo.WeatherRepo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WindViewModel @Inject constructor(
    val weatherRepo: WeatherRepo
) : ViewModel() {
    private var getWindWeatherJob: Job? = null

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(
        WeatherUiState.Success(
            weather = null,
        )
    )

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<WeatherUiState> = _uiState

    private fun intiViewModel() {
        viewModelScope.launch {
            val weather = weatherRepo.getCurrentWeather("london")
            Log.d("Weather", "WindViewModel    ---- Weather: ${weather.toString()} ")
            //weather :String = weatherRepo.getWeather("london")
            _uiState.value = WeatherUiState.Success(weather = weather)
        }
    }

    init {
        intiViewModel()
    }

    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.GetWeather-> {
                viewModelScope.launch {
                        val weather: WeatherResponse? = weatherRepo.getCurrentWeather("London")
                        _uiState.value = WeatherUiState.Success(weather = weather)
                        Log.d("Weather", "WindViewModel    ---- Weather: ${weather.toString()} ")
                }
            }
        }
    }
}
