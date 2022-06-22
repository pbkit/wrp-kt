version = Version.library_generator

plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    implementation("pro.streem.pbandk:pbandk-runtime:0.13.0")
    implementation("pro.streem.pbandk:protoc-gen-pbandk-lib:0.13.0")
}

val sourcesJAR by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").java.srcDirs)
}

publishing {
    repositories { pbkit(project, "wrp-kt") }
    publications {
        register<MavenPublication>(name) {
            artifact(sourcesJAR)
            artifact(releaseJAR)
        }
    }
}
