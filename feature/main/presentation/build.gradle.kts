import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:main:data"))
    implementation(project(":feature:main:domain"))

    implementation(libs.kotlinx.datetime)

    implementation(libs.coil.compose.core)
    implementation(libs.coil.compose)
    implementation(libs.coil)
    implementation(libs.coil.network.ktor)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.main.presentation"
}