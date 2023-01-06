plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.soft.detector"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.soft.detector"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":feature:base"))
    implementation(project(":feature:faceMesh"))

    /**
     * Android Libs
     */
    implementation(Libs.core)
    implementation(Libs.appCompat)
    implementation( Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.navigationDynamicFeature)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUiKtx)
    implementation(Libs.viewmodelKtx)
    implementation(Libs.fragmentKtx)
    implementation(Libs.activityKtx)

    /**
     *  Test Libs
     */

    implementation(Libs.junit)
    implementation(Libs.junitTest)
    implementation(Libs.espressoCore)
}