plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.soft.home"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":feature:base"))
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
}