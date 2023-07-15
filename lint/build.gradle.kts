plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":annotations"))
    compileOnly(libs.kotlin.std.libs)
    compileOnly(libs.lint.api)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    manifest.attributes["Lint-Registry-v2"] = "uk.adbsalam.snapit.lint.SnapItIssueRegistry"
}
