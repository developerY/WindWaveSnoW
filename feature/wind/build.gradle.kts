@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.windwatersnow.android.library)
    alias(libs.plugins.windwatersnow.compose)
}

android {
    namespace = "com.ylabz.windwatersnow.feature.wind"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":data"))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.material3)

    implementation(libs.coil.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.kotlinx.datetime)
    implementation(libs.squareup.retrofit.converter.gson)

    // Permissions
    implementation(libs.androidx.accompanist.permissions)

    implementation(libs.google.accompanist.pager)
    implementation(libs.google.accompanist.pager.indicators)


    // Preview
    //androidx-ui-tooling-preview
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)

    // Not needed
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}