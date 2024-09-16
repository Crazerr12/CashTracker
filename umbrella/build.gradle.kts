plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(17)

    androidTarget()

//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:mediator"))
            implementation(project(":core:utils"))
            implementation(project(":core:database"))

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.decompose)
            implementation(libs.essenty)
            implementation(libs.decompose.extensions)
        }
    }
}

android {
    namespace = "ru.crazerr.cashtracker.umbrella"
    compileSdk = 34
    defaultConfig { minSdk = 21 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}