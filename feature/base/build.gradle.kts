plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.soft.base"
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

    /**
     * Android Libs
     */
    implementation(Libs.core)
    implementation(Libs.appCompat)
    implementation( Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.viewmodelKtx)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUiKtx)
    implementation(Libs.navigationDynamicFeature)

    /**
     *  Test Libs
     */
    implementation(Libs.junit)
    implementation(Libs.junitTest)
    implementation(Libs.espressoCore)
}