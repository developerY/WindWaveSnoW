package com.ylabz.windwatersnow.feature.wear.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ylabz.windwatersnow.data.mapper.windwatersnow
import com.ylabz.windwatersnow.data.windwatersnowRepo
import com.ylabz.windwatersnow.feature.wear.ui.HomeUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val windwatersnowRepository: windwatersnowRepo,
) : ViewModel() {

    init {
        viewModelScope.launch {
            windwatersnowRepository.insert(windwatersnow(title = Math.random().toString()))
        }
    }


    val uiState: StateFlow<HomeUiState> = windwatersnowRepository
        .allGetwindwatersnows()
        .map<List<windwatersnow>, HomeUiState>(::Success)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState.Loading
        )
}
