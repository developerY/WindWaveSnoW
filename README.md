# windwatersnow project.

minSdk = 30

Need to add https://developers.google.com/ml-kit/vision/object-detection/android

This project is designed to showcase a variety of concepts and techniques used in modern Android
development. By exploring this sample, you can learn about:

1. **Building multiple apps from the same codebase**: The project demonstrates how to create
   separate apps for mobile and wearable devices using shared modules and features.
2. **Implementing many kinds of modules**: The sample includes various types of modules such as a
   Kotlin-only module, a test-only module, an Android library, and a dynamic feature module. This
   helps
   you understand how to structure different types of modules within an Android project.
3. **Navigation in a multi-module app**: The project shows how to manage navigation between modules
   and features in a modular Android application.
4. **Consistent build configuration across many modules**: Using convention plugins and version
   catalogs, this project maintains consistent build configurations and dependency management across
   all modules.
5. **Feature modularization**: The project demonstrates how to break down the app into feature
   modules, which allows for better separation of concerns, improves code reusability, and can
   potentially enable dynamic feature delivery using the Android App Bundle.
6. **Testing strategies in a multi-module app**: The sample includes dedicated
   [test modules](https://developer.android.com/studio/test/advanced-test-setup#use-separate-test-modules-for-instrumented-tests)
   and testing utilities to showcase best practices for writing and organizing tests in a modular
   Android app. Instrumentation tests within a feature module are designed to evaluate that specific
   feature in isolation. On the other hand, instrumentation tests housed in a test module are
   intended
   to assess multiple features and app modules collectively.
7. **Shared UI components**: The `:core:ui` module demonstrates how to create a shared library of
   Jetpack Compose UI widgets that can be reused across multiple features and app variants.

By studying this project, you can gain valuable insights into modularizing your own Android app and
adopting best practices for code organization, navigation, build configuration, and testing. This
project follows best practices for Android app modularization. For more information on modularizing
your Android app, refer to the
[Guide to Android App Modularization](https://developer.android.com/topic/modularization).


Move to KSP https://developer.android.com/build/migrate-to-ksp

## Modules Overview

The project is divided into several modules:

- `:app:mobile` - Android app module for phone devices.
- `:app:wear` - Android app module for wearable devices.
- `:build:logic:convention` - Conventions plugins for managing build configurations.
- `:core:testing` - Android library containing testing utilities.
- `:core:ui` - Android library with common Jetpack Compose UI widgets.
- `:core:util` - Kotlin-only module containing utility functions (not an Android library).
- `:data` - Android library for the data layer.
- `:dynamic` - Dynamic delivery module
- `:feature:details` - Android library for the details feature.
- `:feature:list` - Android library for the list feature.
- `:feature:wear:home` - Android library for the wear home feature.
- `:test:navigation` - Test-only module for navigation testing.

Camera Resource --
[exmpale](https://www.youtube.com/watch?v=pPVZambOuG8&t=170s)

Flow of mobile App.
Get the image to store in file and show in compose.


Tasks:
Build WearOS app

Understand -- 
build-logic Conventions 

Features:
List - ListRoute - view
Details - DetailsRoute - New list views Detail View
Photo - PhotoRoute - Just lists Items. 

for a new module add a libary not an app set the feature name correct
Add Feature 
Audio -- 

App starts with the Main Screen -- App / Navigation / main / MainScreen (3 Tabs)



Make Photo Feature working.


Get usecase working.


Dir: location of image file
package com.google.samples.modularization.core.**data**.model
cameraSys.getPicUri()

package com.google.samples.modularization.core.**data**.model
AudioSystem.getUri()

Photolist has both DB(s) in it. 

We need to build Main 
We have two data sets --- 

---


Navigation --
The **NavController** class is used to navigate between screens in the app. It can be used to navigate 
to a specific screen, go back to the previous screen, or pop the back stack to a specific screen.
![nav graph](https://developer.android.com/static/images/topic/libraries/architecture/navigation-principles-start-destination.png)

The Screen class represents the different screens in the app. Each screen has a route property,
which is a string that uniquely identifies the screen.

sealed class Screen(val route: String) {
// MAIN
data object PhotoList: Screen(route = "photo_list_screen") photo_list_screen - no meaning just unique
}

~~--

To use Compose Navigation, you first need to define the navigation graph for your app. 
This can be done using the NavGraph class. The NavGraph constructor takes two parameters:

startDestination: The route of the screen that the app should start on.
route: The route of the navigation graph itself.

----PhotoGraph.kt
fun NavGraphBuilder.photoNavGraph(...) {
navigation(
startDestination = Screen.AddPhoto.route,
route = PHOTO
) {

Once you have defined the navigation graph, you can use the composable() function to add screens to it. 
Each screen in the navigation graph must have a unique route.

{
composable(Screen.MainPhotoList.route) {
// ...
}
composable(Screen.AddPhoto.route) {
// ...
}

To navigate to the add photo screen, you can use the following code:
navController.navigate(Screen.AddPhoto.route)

Compose Navigation also provides a number of other features, such as support for deep links, 
back navigation, and arguments. For more information, please see the 
official documentation: https://developer.android.com/jetpack/compose/navigation.

------
To add a new screen to the NavController, you need to follow these steps:

1. Define the new screen. Create a new class that extends the Screen class. This class should have a 
   route property, which is a string that uniquely identifies the screen.
   // Define the new screen
   data class NewScreen(val route: String = "new_screen") : Screen(route)

2. Add the new screen to the navigation graph. Update the navigation graph to include the new screen. 
   You can do this by adding a composable() function to the navigation graph with the new screen's route.

   // Add the new screen to the navigation graph
   val navigationGraph = NavGraph(
   startDestination = Screen.MainPhotoList.route,
   route = ROOT
   ) {
   composable(NewScreen.route) {
   // ...
   }

3. Navigate to the new screen. Use the NavController to navigate to the new screen. 
   You can do this by calling the navigate() method with the new screen's route.
   Here is an example of how to add a new screen to the NavController:

   // Navigate to the new screen
   navController.navigate(NewScreen.route)

~~--~~~
Here is an example of how to pass an argument to a screen when you navigate to it:

Kotlin
val argument = NavArgument("argument_name") { type = NavType.StringType }

// Navigate to the new screen and pass the argument
navController.navigate(NewScreen.route, listOf(argument))

The argument will be available to the screen as a parameter to the composable() function.

Compose Navigation provides a simple and declarative way to navigate between screens in your Android app. 
By following the steps above, you can easily add new screens to your app and navigate to them.

The approach with Event.kt, Screen.kt, State.kt, and WindViewModel.kt is more explicit and declarative. 
It clearly separates the UI from the logic of the app, making the code more modular, reusable, and testable. 
**However**, it can be more verbose and complex to implement, especially for large apps.

The approach with Route.kt, UiState.kt, and WindViewModel.kt is more concise and easier to implement. 
It also makes it easier to handle navigation between screens. However, it is less explicit and declarative, 
and it can be more difficult to separate the UI from the logic of the app.


/**
* **ViewModel:** The ViewModel is a data holder that stores the state of the UI. It is responsible 
* for providing the data to the UI and handling any user interactions. The ViewModel is also responsible 
* for persisting the state of the UI, so that it can be restored when the app is restarted.

**UiState:** The UiState is a representation of the current state of the UI. It is a data object that 
contains all of the data that the UI needs to render. The UiState is updated whenever the ViewModel changes its state.

**Screen/Route:** The Screen/Route represents a specific screen in the app. It is responsible for 
defining the layout of the screen and the behavior of the UI elements on the screen. 
The Screen/Route also contains a reference to the ViewModel that is responsible for providing the data to the UI.

**Repository:** The Repository is a data abstraction layer that provides access to data sources. 
It is responsible for fetching, caching, and persisting data. The Repository hides the implementation details 
of the data sources from the ViewModel, which makes the ViewModel more reusable and testable.

**Usage:**

```kotlin

Repo
class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getUser(userId: Int): User {
        // Check the cache first
        val user = userDao.getUserById(userId)
        if (user != null) {
            return user
        }

        // Fetch the user from the database
        val userFromDb = userDao.getUserById(userId)
        if (userFromDb != null) {
            return userFromDb
        }

        // Fetch the user from the network and cache it
        val userFromNetwork = api.getUser(userId)
        userDao.insertUser(userFromNetwork)
        return userFromNetwork
    }
}


ViewModel ---
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val user: State<UserUiState> = mutableStateOf(UserUiState())

    init {
        // Collect the user flow and update the UI state
        userRepository.getUser(1).collect { user ->
            this.user.value = UserUiState(user)
        }
    }
}


Ui State ---
data class UserUiState(
    val user: User?
)


Screen --- 
@Composable
fun UserScreen(viewModel: UserViewModel) {
    val user = viewModel.user.value

    if (user != null) {
        Text("User: ${user.name}")
    } else {
        Text("Loading...")
    }
}

@Composable
fun MainScreen() {
    val viewModel = UserViewModel()

    UserScreen(viewModel)
}
```


**Benefits:**

* The ViewModel, UiState, Screen/Route, and Repository provide a declarative and reactive way to build UIs in Compose.
* The ViewModel, UiState, and Screen/Route separate the UI state and UI code from the data access code, which makes the code more modular and testable.
* The Repository hides the implementation details of the data sources from the ViewModel, which makes the ViewModel more reusable and testable.

**Overall, the ViewModel, UiState, Screen/Route, and Repository are essential components for building modern Android UIs with Compose. 
They provide a way to build UIs that are declarative, reactive, modular, and testable.**

[Data] -> [DAO] -> [Repository] ->  [UseCase] -> [ViewModel] -> [Compose]

Look for:
imageCaptureUseCase.takePicture

Only one pic URL is taken at a time. It is held until a good pic is found. Once a good pic is found the URL is used in the Add new windwatersnow.

When retaking the picture. Delete the old file and keep only the new. 


NEXT Task -- Show all images in the dir in the Cat tab.

 UI - is 
Route / Screen / Content - Content -- has -- preview

ViewModel
The ViewModel should alwasy have a UI state that is a flow
The ViewModel should have a when statemenet to hanel events.


StateFlow is a newer and more modern approach for managing state in a ViewModel compared to mutableStateOf. 
It offers several advantages over mutableStateFlow.

**Lifecycle awareness is important for managing resources and ensuring that your app behaves correctly 
when the lifecycle of a composable changes.**


The SavedStateHandle is a key-value map that allows you to save and retrieve state information across process deaths in Android Jetpack Lifecycle. 
It's particularly useful for preserving data that should persist when the device is rotated or the app is relaunched.

```agsl
 AddTodoCalPhoto(
                               todoDate, // Time Date stored in DB
                               imageUri,
                               audioUri,
                               windwatersnowTitle,
                               windwatersnowDescription,
                               windwatersnowInfoUpdate,
                               // imageUri = EMPTY_IMAGE_URI
                               todoViewModel::insert,
                               resetVals,
                               //currenTodoPhoto,
                               //windwatersnowTitle,
                               //windwatersnowDescription,
                               //windwatersnowInfoUpdate,
                               // backStackEntry.arguments?.getString("imgPath"), // NOTE: XXX Not working
                               // backStackEntry.arguments?.getString("userId")
                           ) { path ->
                               navController.navigate(path)
                           }
```


UIState 
Loading 
Sucess  data class Success - holds the data
```
sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(
        val title: String,
        val description: String
    ) : DetailsUiState
}

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val data: List<MyModel>) : HomeUiState
}

sealed interface ListUiState {
    object Loading : ListUiState
    data class Success(val data: List<MyModelUiState>) : ListUiState
}
```

Adding Compose resources
https://foso.github.io/Jetpack-Compose-Playground/compose_projects/

Weather Code is here:
https://github.com/hoc081098/WeatherApp_MVI_sample/blob/try_mvi/app/src/main/java/com/hoc/weatherapp/data/remote/OpenWeatherMapApiService.kt


Examples:
https://github.com/bachhoan88/MAD-Clean-Architecture
