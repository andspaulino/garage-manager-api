
dependencies {
    // Module dependencies
    implementation(project(":domain"))

    // Serialization for DTOs
    implementation(libs.ktor.serialization.kotlinx.json)
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    // Testing
    testImplementation(libs.kotlin.test.junit)
}
