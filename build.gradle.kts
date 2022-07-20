import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath(kotlin("gradle-plugin", "1.6.21"))
    }
}

allprojects {
    group = "dev.pbkit.wrp"
}

subprojects {
    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
            }
        }
    }
    afterEvaluate {
        tasks {
            // publish
            filter { it.name.contains("publish") }
                .forEach { task -> task.dependsOn(getByName("assemble")) }
        }
    }
}
