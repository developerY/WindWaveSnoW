package com.ylabz.windwatersnow

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

@Suppress("UnstableApiUsage")
internal fun configureAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 31
            testInstrumentationRunner = "com.ylabz.windwatersnow.testing.HiltTestRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        buildFeatures.buildConfig = false
    }
}
