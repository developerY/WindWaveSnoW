@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.windwatersnow.android.library)
    alias(libs.plugins.windwatersnow.compose)
}

android {
    namespace = "com.ylabz.windwatersnow.core.ui"
}

dependencies {
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.accompanist.permissions)

    // Preview
    //androidx-ui-tooling-preview
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)

}