package com.ylabz.windwatersnow.wind.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
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
        WindUiState.Success(
            weather = "",
            // data = emptyList() ,audioFiles = emptyList(), photoFiles = emptyList()
        )
    )

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<WindUiState> = _uiState

    private fun intiViewModel() {
        viewModelScope.launch {
            val weather = weatherRepo.getWeather("london")
            Log.d("Weather", weather)
            //weather :String = weatherRepo.getWeather("london")
            _uiState.value = WindUiState.Success(weather = weather)
        }
    }

    init {
        intiViewModel()
    }

    fun onEvent(event: WindEvent) {
        when (event) {
            is WindEvent.GetWeather-> {
                viewModelScope.launch {
                        val weather:String = weatherRepo.getWeather("London")
                        Log.d("Weather", weather)
                }
            }
        }
    }
}
