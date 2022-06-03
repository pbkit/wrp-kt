// group = "dev.pbkit.wrp"

// buildscript {
//     repositories {
//         mavenCentral()
//         google()
//     }
//     dependencies {
//         classpath("com.android.tools.build:gradle:7.1.1")
//         classpath(kotlin("gradle-plugin"))
//     }
// }
//
// allprojects {
//     repositories {
//         mavenCentral()
//         google()
//     }
// }

plugins {
    // kotlin("jvm") version "1.6.21" apply false
    // id("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    // id("com.android.application")
    // id("com.android.library")
    // id("org.jetbrains.kotlin.android")
}
