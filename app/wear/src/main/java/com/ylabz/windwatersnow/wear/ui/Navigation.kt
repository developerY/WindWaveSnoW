package com.ylabz.windwatersnow.wear.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.ylabz.windwatersnow.feature.wear.ui.HomeRoute

@Composable
fun MainNavigation(
    navController: NavHostController = rememberSwipeDismissableNavController()
) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeRoute()
        }
    }
}

@Composable
fun QuickStart() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .background(Color.Blue) // Set the background color to blue
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Hi")
    }
}