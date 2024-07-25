plugins {
    `kotlin-dsl`
}

group = "com.ylabz.windwatersnow.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.tools.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {
        register("windwatersnowAndroidApplication") {
            id = "windwatersnow.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("windwatersnowAndroidLibrary") {
            id = "windwatersnow.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("windwatersnowAndroidTest") {
            id = "windwatersnow.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("windwatersnowCompose") {
            id = "windwatersnow.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("windwatersnowDynamic") {
            id = "windwatersnow.dynamic"
            implementationClass = "DynamicFeatureConventionPlugin"
        }
    }
}
