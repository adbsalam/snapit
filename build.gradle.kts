import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.MavenPublishBasePlugin
import com.vanniktech.maven.publish.SonatypeHost

buildscript {}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.maven.publish) apply false
}

subprojects {
    plugins.withType(MavenPublishBasePlugin::class.java).configureEach {
        project.extensions.getByType<MavenPublishBaseExtension>().apply {
            @Suppress("UnstableApiUsage")
            coordinates("uk.adbsalam.snapit", project.name, "1.0.2")
            publishToMavenCentral(SonatypeHost.S01)

            @Suppress("UnstableApiUsage")
            pom {
                name.set("SnapIt")
                description.set("Auto Paparazzi test generation library")
                inceptionYear.set("2022")
                url.set("https://github.com/MuhammadAbdulSalam/snapit")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("adb_salam")
                        name.set("Abdul Salam")
                        url.set("https://github.com/MuhammadAbdulSalam")
                    }
                }
                scm {
                    url.set("https://github.com/MuhammadAbdulSalam/snapit")
                    connection.set("scm:git:git://github.com/MuhammadAbdulSalam/snapit.git")
                    developerConnection.set("scm:git:ssh://git@github.com:MuhammadAbdulSalam/snapit.git")
                }
            }
        }
    }
}
