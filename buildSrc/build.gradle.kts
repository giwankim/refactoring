plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // Add a dependency on the Kotlin Gradle plugin, so that convention plugins can apply it.
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.plugin.serialization)
    implementation(libs.ktlint.gradle.plugin)
}
