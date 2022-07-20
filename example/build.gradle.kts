plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32
    packagingOptions {
        resources.excludes.add("**/*.proto")
    }
    defaultConfig {
        applicationId = "dev.pbkit.wrp.android.example"
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
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta03"
    }
    sourceSets {
        getByName("main") { java.srcDirs("src/main/kotlin") }
    }
    namespace = "dev.pbkit.wrp.example"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":webview"))
    implementation(project(":webview-compose"))
    implementation("com.google.protobuf:protobuf-java:3.21.2")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.2")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:1.2.0-beta03")
    implementation("androidx.compose.material:material:1.2.0-beta03")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-beta03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-beta03")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-beta03")
}
