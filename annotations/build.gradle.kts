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
    description = "Annotations library to provide @SnapIt annotation for Snapshot Testing for components such as components, screens, dark and light modes, gifs"
}

