plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
}

dependencies {
    compileOnly(libs.kotlin.std.libs)
    compileOnly(libs.lint.api)
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
