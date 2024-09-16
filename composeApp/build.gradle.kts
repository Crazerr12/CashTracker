import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
//    alias(libs.plugins.cocoapods)
}

kotlin {
    jvmToolchain(17)
    androidTarget {
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }

//    wasmJs {
//        browser()
//        binaries.executable()
//    }

//    cocoapods {
//        name = "shared"
//        version = "1.0"
//        summary = "cashTracker iOS Kit"
//        homepage = "https://github.com/crazerr12"
//        license = "Free"
//
//        ios.deploymentTarget = "16.0"
//
//        framework {
//            baseName = "CashKit"
//
//            isStatic = false
//
//            export(libs.decompose)
//            export(libs.essenty)
//            export(project(":core:database"))
//
//            @OptIn(ExperimentalKotlinGradlePluginApi::class)
//            transitiveExport = false
//        }
//
//        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
//        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
//    }
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:compose"))
            implementation(project(":core:mediator"))
            implementation(project(":umbrella"))
            implementation(libs.decompose)
            implementation(libs.essenty)
            implementation(libs.decompose.extensions)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.coil)
            implementation(libs.coil.network.ktor)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.core)
            implementation(libs.composeIcons.featherIcons)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.multiplatformSettings)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.kstore)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(compose.uiTooling)
            implementation(libs.androidx.activityCompose)
            implementation(libs.kotlinx.coroutines.android)
        }

//        iosMain.dependencies {
//            api(libs.decompose)
//            api(libs.essenty)
//            implementation(libs.decompose.extensions)
//            implementation(libs.ktor.client.darwin)
//        }

    }
}

android {
    namespace = "ru.crazerr.cashtracker"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34

        applicationId = "ru.crazerr.cashtracker.androidApp"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

//https://developer.android.com/develop/ui/compose/testing#setup
dependencies {
    androidTestImplementation(libs.androidx.uitest.junit4)
    debugImplementation(libs.androidx.uitest.testManifest)
    //temporary fix: https://youtrack.jetbrains.com/issue/CMP-5864
    androidTestImplementation("androidx.test:monitor") {
        version { strictly("1.6.1") }
    }
}
