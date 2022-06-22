plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation(kotlin("gradle-plugin", "1.6.21"))
    implementation("com.android.tools.build:gradle:7.2.1")
}
