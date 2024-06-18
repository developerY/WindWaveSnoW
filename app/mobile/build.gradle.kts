@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.windwatersnow.android.application)
    alias(libs.plugins.windwatersnow.compose)
    alias(libs.plugins.mapsplatform.secrets)
}

android {
    namespace = "com.ylabz.windwatersnow"

    defaultConfig {
        applicationId = "com.ylabz.windwatersnow"
    }
    secrets {
        defaultPropertiesFileName = "secrets.defaults.properties"
    }

    // dynamicFeatures += setOf(":dynamic")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:wind"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // implementation(libs.androidx.activity.compose)
    // implementation(platform(libs.androidx.compose.bom))

    // implementation(libs.androidx.ui)
    // implementation(libs.androidx.ui.graphics)
    // implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)

    // Paging
    implementation(libs.google.accompanist.pager)
    implementation(libs.google.accompanist.pager.indicators)

    implementation(libs.kotlinx.datetime)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)
    //implementation(libs.androidx.hilt.navigation.compose)
    //implementation(libs.androidx.hilt.navigation.compose.navigation)


    // TESTING
    //implementation(libs.androidx.ui.tooling)
    //implementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}