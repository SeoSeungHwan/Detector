import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions{

    /**
     * Android Libs
     */
    const val coreKtx = "1.7.0"
    const val appCompat = "1.5.1"
    const val material = "1.7.0"
    const val constraintLayout = "2.1.4"
    const val cameraX = "1.2.0"
    const val faceMeshDetection = "16.0.0-beta1"
    const val coroutinesCore = "1.5.2"
    const val coroutinesAndroid = "1.6.0"
    const val viewmodelKtx = "2.4.0"
    const val fragmentKtx = "1.5.5"
    const val activityKtx = "1.6.1"
    const val navigation = "2.4.2"


    /**
     * Test Libs
     */
    const val junit = "4.13.2"
    const val junitTest = "1.1.4"
    const val espresso = "3.5.0"


}

object Libs{
    /**
     * Android Libs
     */
    const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val faceMeshDetection = "com.google.mlkit:face-mesh-detection:${Versions.faceMeshDetection}"
    const val cameraCore = "androidx.camera:camera-core:${Versions.cameraX}"
    const val cameraCamera2 ="androidx.camera:camera-camera2:${Versions.cameraX}"
    const val cameraLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
    const val cameraView = "androidx.camera:camera-view:${Versions.cameraX}"
    const val cameraExtensinos = "androidx.camera:camera-extensions:${Versions.cameraX}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodelKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationDynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    /**
     * Test Libs
     */
    const val junit = "junit:junit:${Versions.junit}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitTest}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}


fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}