package com.ylabz.windwatersnow.feature.wear.ui

import com.ylabz.windwatersnow.data.mapper.windwatersnow


sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val data: List<windwatersnow>) : HomeUiState
}
