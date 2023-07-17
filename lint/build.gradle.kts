plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kover)
}

dependencies {
    compileOnly(libs.kotlin.std.libs)
    compileOnly(libs.lint.api)

    testImplementation(libs.junit)
    testImplementation(libs.lint.test)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

mavenPublishing {
    signAllPublications()
}

tasks.jar {
    manifest.attributes["Lint-Registry-v2"] = "uk.adbsalam.snapit.lint.SnapItIssueRegistry"
}
