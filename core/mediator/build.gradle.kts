plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget()

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:compose"))
                implementation(project(":core:database"))
                implementation(project(":core:utils"))

                implementation(project(":feature:main:presentation"))
                implementation(project(":feature:budget:presentation"))
                implementation(project(":feature:transactions:presentation"))
                implementation(project(":feature:settings:presentation"))

                implementation(libs.koin.core)

                implementation(libs.coil.compose)
                implementation(libs.coil)

                implementation(libs.androidx.material3.adaptive)

                implementation(libs.decompose)
                implementation(libs.essenty)
                implementation(libs.decompose.extensions)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
            }
        }
    }
}

android {
    namespace = "ru.crazerr.cashtracker.core.mediator"
    compileSdk = 34
    defaultConfig { minSdk = 21 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
