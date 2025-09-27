plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ktor) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
}

allprojects {
    group = "io.github.andspaulino"
    version = "0.0.1"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    
    dependencies {
        val implementation by configurations
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
    }
}