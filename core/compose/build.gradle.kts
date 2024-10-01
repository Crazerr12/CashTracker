import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("kmp.compose.plugin")
}

commonMainDependencies {
    implementation(project(":core:utils"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.core.compose"
}