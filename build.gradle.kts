buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath(kotlin("gradle-plugin"))
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}
