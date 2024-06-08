@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.windwatersnow.android.library)
    alias(libs.plugins.windwatersnow.compose)
}

android {
    namespace = "com.ylabz.windwatersnow.feature.wear.home"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":data"))

    // Arch Components
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Compose
    implementation(libs.androidx.wear.compose.material)
    implementation(libs.androidx.wear.compose.foundation)

    implementation(libs.kotlinx.datetime)
}