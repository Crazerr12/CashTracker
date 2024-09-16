plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)
    androidTarget()

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:compose"))
            implementation(project(":core:utils"))
            implementation(project(":feature:settings:data"))
            implementation(project(":feature:settings:domain"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.decompose)
            implementation(libs.decompose.extensions)
            implementation(libs.essenty)

            implementation(libs.kotlinx.datetime)
        }

        androidMain.dependencies {

        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "ru.crazerr.cashtracker.feature.settings.presentation"
    compileSdk = 34
    defaultConfig { minSdk = 21 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}