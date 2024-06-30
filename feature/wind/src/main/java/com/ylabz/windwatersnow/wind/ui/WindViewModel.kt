package com.ylabz.windwatersnow.wind.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ylabz.windwatersnow.network.model.AudioSystem
import com.ylabz.windwatersnow.network.repo.WeatherRepo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Marine Weather Data
 * Example URL for Marine Weather Data:
 * https://api.weather.gov/gridpoints/MLB/25,69/forecast
 *
 * Interface ['Retorfit'] is NOAAWeather<Service> file WeatherResponse
 * Retrofit is in RetrofitClient could be place in above as companion object
 *
 * data classes are in the NOAAMarine<WeatherResponse.kt> file
 *
 *
 */
@HiltViewModel
class WindViewModel @Inject constructor(
    val weatherRepo: WeatherRepo,
    private val audioFun: AudioSystem, //TODO move to Repo
) : ViewModel() {
    private var getWindWeatherJob: Job? = null


    private var _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<WeatherUiState> = _uiState

    private fun intiViewModel() {
        viewModelScope.launch {
            _uiState.value  = WeatherUiState.Success(weather = null)
            val weather = weatherRepo.openCurrentWeather((_uiState.value as WeatherUiState.Success).location)
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
            // This is for the description
            is WeatherEvent.SetLocation -> {
                Log.d("Weather", "This is the dis ${event.value}")
                viewModelScope.launch {
                    val response = weatherRepo.openCurrentWeather(event.value)
                    if (response != null) {
                        _uiState.value  = WeatherUiState.Success(
                            location = event.value,
                            weather = response)

                    } else {
                        _uiState.value = WeatherUiState.Error("Error: City not found")
                    }
                }
            }

            is WeatherEvent.StartCaptureSpeech2Txt -> {
                viewModelScope.launch {
                    // actual work happens in the use case.
                    audioFun.startSpeechToText(event.updateText, event.finished)
                    //_eventFlow.emit(AddPhotodoEvent.getTextFromSpeach)
                    // Not sure what to do ...
                }
            }

            WeatherEvent.DismissError -> {
                viewModelScope.launch {
                    _uiState.value = WeatherUiState.Success(weather = null)
                    val updatedUiState = (_uiState.value as WeatherUiState.Success).copy(
                        location = "none"
                    )
                    _uiState.emit(updatedUiState)
                }
            }
        }
    }
}

