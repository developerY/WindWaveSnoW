package com.ylabz.windwatersnow.wind.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.ylabz.windwatersnow.wind.ui.components.snow.SnowboardRoute
import com.ylabz.windwatersnow.wind.ui.components.surf.SurferWindRoute
import com.ylabz.windwatersnow.wind.ui.components.wind.SailorWindRoute


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun SwipeableViews(
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> SailorWindRoute(paddingValues, navTo)//PageView("View 1", Color.Red)
                1 -> SurferWindRoute(paddingValues, navTo)//PageView("View 2", Color.Green)
                2 -> SnowboardRoute(paddingValues, navTo)//PageView("View 3", Color.Blue)
            }
        }
    }
}

@Composable
fun PageView(text: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}
