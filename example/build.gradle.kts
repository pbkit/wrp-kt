plugins {
    // kotlin("android") version "1.6.21"
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // id("com.android.application") version "7.2.0" apply false
    // id("com.android.library") version "7.2.0" apply false
    // id("org.jetbrains.kotlin.android") version "1.5.31" apply false
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "dev.pbkit.wrp.example"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta03"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:1.2.0-beta03")
    implementation("androidx.compose.material:material:1.2.0-beta03")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-beta03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-beta03")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-beta03")
}
