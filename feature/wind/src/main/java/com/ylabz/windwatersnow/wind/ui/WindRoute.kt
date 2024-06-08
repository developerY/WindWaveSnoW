package com.ylabz.windwatersnow.wind.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ylabz.windwatersnow.core.ui.ExpandableToggleButton
import com.ylabz.windwatersnow.core.ui.Loading


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindRoute(
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    //navController: NavController,
    //onGoToItem: (Long) -> Unit,
    //modifier: Modifier = Modifier,
    viewModel: WindViewModel = hiltViewModel()
) {
    val onEvent: (WindEvent) -> Unit = viewModel::onEvent
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    WindScreen(
        paddingValues = paddingValues,
        onEvent = onEvent,
        windUiState = state,
        navTo = navTo,
    )
}

@Composable
internal fun WindScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WindEvent) -> Unit,
    windUiState: WindUiState,
    navTo: (String) -> Unit,
) {
    when (windUiState) {
        WindUiState.Loading -> Loading(modifier)

        is WindUiState.Success -> WindContent(
            modifier,
            paddingValues = paddingValues,
            onEvent = onEvent,
            avFiles = Pair(windUiState.photoFiles, windUiState.audioFiles),
            navTo = navTo
        )

        WindUiState.Error -> TODO()
    }
}

@Composable
internal fun WindContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WindEvent) -> Unit,
    avFiles: Pair<List<String>, List<String>>,
    navTo: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "Hello Wind")
    }
}
