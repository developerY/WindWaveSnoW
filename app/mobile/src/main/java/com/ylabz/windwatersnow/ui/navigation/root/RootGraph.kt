package com.ylabz.windwatersnow.ui.navigation.root

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ylabz.windwatersnow.core.ui.MAIN
import com.ylabz.windwatersnow.core.ui.ROOT
import com.ylabz.windwatersnow.ui.navigation.main.MainScreen


@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    //padding:PaddingValues,
    startDestination: String = MAIN
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        route = ROOT,
    ) {
        // mainNavGraph(navHostController)
        //photoNavGraph(navHostController)
        composable(route = MAIN) {
            MainScreen()
        }
    }
}

/*
composable(route = MAIN) {
            MainScreen(navHostController)
        }
 */