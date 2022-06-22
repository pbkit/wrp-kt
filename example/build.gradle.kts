import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf")
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
    namespace = "dev.pbkit.wrp.example"
}

protobuf {
    val classifier = Runtime.getRuntime().exec("uname -p")
        .inputStream
        .bufferedReader()
        .readLine()
        .trim()
        .let { processor -> if (processor == "arm") ":osx-x86_64" else "" }
    protoc { artifact = "com.google.protobuf:protoc:3.19.4$classifier" }
    plugins {
        id("pbandk") {
            artifact = "pro.streem.pbandk:protoc-gen-pbandk-jvm:0.13.0:jvm8@jar"
        }
    }
    generateProtoTasks {
        val generator = "${project(":generator").buildDir}/libs/generator-${Version.library_generator}.jar"
        all().forEach { task ->
            task.dependsOn(":generator:jar")
            task.builtins {
                remove("java")
            }
            task.plugins {
                id("pbandk") {
                    option("log=debug")
                    option("kotlin_package=dev.pbkit.wrp")
                    option("pbandk_out=src/main/kotlin")
                    option("kotlin_service_gen=$generator|dev.pbkit.wrp.gen.Generator")
                }
            }
        }
    }
}

dependencies {
    protobuf(files("$projectDir/protobuf"))
    implementation(project(":generator"))
    implementation(project(":core"))
    implementation(project(":webview-compose"))
    implementation("pro.streem.pbandk:pbandk-runtime:0.13.0")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:1.2.0-beta03")
    implementation("androidx.compose.material:material:1.2.0-beta03")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-beta03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-beta03")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-beta03")
}
