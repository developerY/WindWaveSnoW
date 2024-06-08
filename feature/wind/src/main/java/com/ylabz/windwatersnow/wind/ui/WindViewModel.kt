package com.ylabz.windwatersnow.wind.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WindViewModel @Inject constructor(

) : ViewModel() {
    private var getPhotodoJob: Job? = null

    /*private val _uiState = MutableStateFlow(WindUiState.Loading)
    val uiState: StateFlow<WindUiState> = _uiState.collectAsStateWithLifecycle(
        viewModelScope
    )*/


    init {
        intiViewModel()
    }

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(
        WindUiState.Success(
            data = emptyList()
            //,audioFiles = emptyList(), photoFiles = emptyList()
        )
    )

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<WindUiState> = _uiState


    private fun intiViewModel() {
        var audioFiles: List<String> = emptyList()
        var photoFiles: List<String> = emptyList()
        viewModelScope.launch {
            
        }
    }

    fun onEvent(event: WindEvent) {
        when (event) {
            is WindEvent.GetWeather-> TODO()
        }
    }
}
