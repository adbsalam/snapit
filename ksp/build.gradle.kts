plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":annotations"))
    implementation(libs.google.ksp)
    implementation(libs.junit)
    implementation(libs.paparazzi)
    implementation(libs.kotlin.poet.ksp)
    implementation(libs.kotlin.poet)
}