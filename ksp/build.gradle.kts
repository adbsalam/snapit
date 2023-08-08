plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kover)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

mavenPublishing {
    signAllPublications()
    description = "Code gen module to generate code for @Snapit Annotations. This will generate JUnit tests"
}

dependencies {
    implementation(project(":annotations"))
    implementation(libs.google.ksp)
    implementation(libs.junit)
    implementation(libs.paparazzi)
    implementation(libs.kotlin.poet.ksp)
    implementation(libs.kotlin.poet)

    testImplementation(kotlin("test"))
    testImplementation(libs.mockk)
    testImplementation(libs.mockito)
}