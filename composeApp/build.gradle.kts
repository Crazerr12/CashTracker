import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("kmp.compose.application.plugin")
//    alias(libs.plugins.cocoapods)
}

//kotlin {
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

//    iosRegularFramework {
//        baseName = "ComposeApp"
//        isStatic = true
//    }
//}

commonMainDependencies {
    implementation(project(":core:compose"))
    implementation(project(":core:mediator"))
    implementation(project(":umbrella"))
    implementation(libs.decompose)
    implementation(libs.essenty)
    implementation(libs.decompose.extensions)
    implementation(libs.coil)
    implementation(libs.coil.network.ktor)
    implementation(libs.ktor.core)
    implementation(libs.composeIcons.featherIcons)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.multiplatformSettings)
    implementation(libs.kstore)
}

//iosMainDependencies {
//    api(libs.decompose)
//    api(libs.essenty)
//    implementation(libs.decompose.extensions)
//    implementation(libs.ktor.client.darwin)
//}


android {
    namespace = "ru.crazerr.cashtracker"

    defaultConfig {
        applicationId = "ru.crazerr.cashtracker.androidApp"
    }
}

//https://developer.android.com/develop/ui/compose/testing#setup
dependencies {
    //temporary fix: https://youtrack.jetbrains.com/issue/CMP-5864
    androidTestImplementation("androidx.test:monitor") {
        version { strictly("1.6.1") }
    }
}
