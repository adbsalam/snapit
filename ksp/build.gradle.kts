plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

mavenPublishing {
    signAllPublications()
}

dependencies {
    implementation(project(":annotations"))
    implementation(libs.google.ksp)
    implementation(libs.junit)
    implementation(libs.paparazzi)
    implementation(libs.kotlin.poet.ksp)
    implementation(libs.kotlin.poet)
}