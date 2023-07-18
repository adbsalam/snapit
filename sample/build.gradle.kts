import uk.adbsalam.snapit.plugin.snapIt

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
}

android {
    namespace = "uk.adbsalam.snapit"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

snapIt {
    testDir("uk/adbsalam/snapit/sample")
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.material.two)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.junit)
}