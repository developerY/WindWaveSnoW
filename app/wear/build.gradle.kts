@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.windwatersnow.android.application)
    alias(libs.plugins.windwatersnow.compose)
    alias(libs.plugins.mapsplatform.secrets)
}

android {
    namespace = "com.ylabz.windwatersnow.wear"

    defaultConfig {
        applicationId = "com.ylabz.windwatersnow.wear"
    }
}

dependencies {
    implementation(project(":feature:wear:home"))

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.wear.compose.material)
    implementation(libs.androidx.wear.compose.navigation)
}
