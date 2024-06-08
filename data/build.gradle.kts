@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.windwatersnow.android.library)
    alias(libs.plugins.ksp)
    //alias(libs.plugins.apollo.graphql)
    alias(libs.plugins.mapsplatform.secrets)
}

android {
    namespace = "com.ylabz.windwatersnow.data"

    buildFeatures {
        aidl = false
        buildConfig = true
        renderScript = false
        shaders = false
    }

    /*apollo {
        service("service") {
            packageName.set("com.ylabz.windwatersnow.data")
        }
    }*/

    secrets {
        defaultPropertiesFileName = "secrets.defaults.properties"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)


    // TF Lite
    //implementation(libs.tensorflow.lite.support)
    implementation(libs.tensorflow.lite.metadata)
    implementation(libs.tensorflow.lite.task.vision)
    //implementation(libs.tensorflow.lite.gpu)
    // MLKit
    implementation(libs.play.services.mlkit.face.detection)

    implementation(libs.kotlinx.datetime)
}
