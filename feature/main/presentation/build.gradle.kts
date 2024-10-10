import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:main:data"))
    implementation(project(":feature:main:domain"))
    implementation(project(":feature:account:presentation:api"))
    implementation(project(":feature:transaction:presentation:api"))
    implementation(project(":feature:category:presentation:api"))

    implementation(libs.kotlinx.datetime)

    implementation(libs.coil.compose.core)
    implementation(libs.coil.compose)
    implementation(libs.coil)
    implementation(libs.coil.network.ktor)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.main.presentation"
}