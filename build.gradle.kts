// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.tools.build.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.gradle.plugin)
    }
}


plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false

    alias(libs.plugins.windwatersnow.android.application) apply false
    alias(libs.plugins.windwatersnow.android.library) apply false
    alias(libs.plugins.windwatersnow.android.test) apply false
    alias(libs.plugins.windwatersnow.compose) apply false
    alias(libs.plugins.windwatersnow.dynamic) apply false


}
true // Needed to make the Suppress annotation work for the plugins block
