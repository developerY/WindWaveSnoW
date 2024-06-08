package com.ylabz.windwatersnow.ui.navigation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ylabz.windwatersnow.core.ui.MAIN
import com.ylabz.windwatersnow.core.ui.Screen
import com.ylabz.windwatersnow.wind.ui.WindRoute

/**
 * This code defines the main navigation graph for an Android application using Jetpack Compose
 * and the Navigation component. The navigation graph specifies the destinations and routes within
 * the app, allowing users to navigate between different screens.
 *
 * Composable Function:
 * - `MainNavGraph(navController: NavHostController, padding: PaddingValues)`: The primary composable
 *   function that sets up the main navigation graph for the application. It configures the NavHost
 *   with various composable destinations corresponding to different screens.
 *
 * Destinations and Composables:
 * - The code defines several composable destinations for the NavHost.
 *   1. `Screen.Task.route`: Represents the "Task" screen and displays the text "Next Task" along
 *      with a windwatersnowScreen composable.
 *   2. `Screen.PhotoT.route`: Represents the "Photo" screen and displays a PhotoRoute composable.
 *   3. `Screen.List.route`: Represents the "List" screen and displays a ListRoute composable.
 *   4. "details/{id}": A dynamic route that takes an "id" parameter and displays a DetailsRoute
 *      composable for showing details related to a specific item.
 *   5. `Screen.Cat.route`: Represents the "Cat" screen and displays the text "Next Cat" along with
 *      a ListwindwatersnowScreen composable.
 *   6. `photoNavGraph(navController, padding)`: Invokes a separate navigation graph for the "Photo" tab.
 *
 * Overall, this code defines the structure of the main navigation graph for the app, setting up
 * different destinations and their associated composable functions. Users can navigate between
 * these screens using the provided NavHostController, creating a smooth navigation experience.
 */


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        route = MAIN,
        startDestination = Screen.Water.route
    ) {

        composable(
            Screen.Wind.route,
        ) {
            WindRoute(
                paddingValues = padding,
                navTo = {path -> navController.navigate(path)}
            )
        }

        composable(
            Screen.Water.route
        ) {
            Text("Water View")
        }

        composable(
            Screen.Snow.route
        ) {
            Text("Snow View")
        }

        //windwatersnowNavGraph(navController, padding)

        /*composable(
            "details/{id}",
            listOf(navArgument("id") { type = NavType.LongType }),
        ) {
            DetailsRoute(
                onGoBack = {
                    navController.popBackStack()
                },
            )
        }

        composable(
            Screen.Cat.route
        ) {
            CatRoute (
                padding,
                navController,
                onGoToItem = { id ->
                    navController.navigate("details/$id")
                },
            )
        }

        composable(
            Screen.PhotoT.route
        ) {
            PhotoRoute(
                padding,
                navController,
                onGoToItem = { id ->
                    navController.navigate("details/$id")
                },
            )
        }

        composable(
            "details/{id}",
            listOf(navArgument("id") { type = NavType.LongType }),
        ) {
            DetailsRoute(
                onGoBack = {
                    navController.popBackStack()
                },
            )
        }

        composable(
            Screen.Cat.route
        ) {
            CatRoute (
                padding,
                navController,
                onGoToItem = { id ->
                    navController.navigate("details/$id")
                },
            )
        }*/
    }
}
