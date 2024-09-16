plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(17)
    androidTarget()

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(project(":core:database"))
            implementation(project(":core:utils"))
            implementation(project(":feature:cashback:domain"))

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.room.runtime)

            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "ru.crazerr.cashtracker.feature.cashback.data"
    compileSdk = 34
    defaultConfig { minSdk = 21 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}