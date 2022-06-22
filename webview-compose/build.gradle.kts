version = Version.library_webview_compose

plugins {
    `android-library`
    `kotlin-android`
    `maven-publish`
}

android {
    namespace = "dev.pbkit.wrp.android.compose"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta03"
    }
}

dependencies {
    implementation(project(":webview"))
    implementation(project(":core"))
    implementation("androidx.compose.ui:ui:1.2.0-beta03")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
}

val sourcesJAR by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

publishing {
    repositories { pbkit(project, "wrp-kt") }
    publications {
        register<MavenPublication>(name) {
            artifact(sourcesJAR)
            artifact(releaseAAR)
        }
    }
}

