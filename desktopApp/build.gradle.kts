import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()

    sourceSets {
        jvmMain.dependencies {
            implementation(project(":core:compose"))
            implementation(project(":core:mediator"))
            api(project(":umbrella"))
            api(project(":core:utils"))

            implementation(compose.desktop.currentOs)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.coroutines.swing)

            api(libs.decompose)
            api(libs.decompose.extensions)
            implementation(libs.koin.core)
        }
    }
}



compose.desktop {
    application {
        mainClass = "ru.crazerr.cashtracker.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.crazerr.cashtracker.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}