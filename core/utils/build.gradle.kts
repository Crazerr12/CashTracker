import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("kmp.compose.plugin")
}

commonMainDependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.decompose)
    implementation(libs.essenty)
    implementation(libs.decompose.extensions)
}

android {
    namespace = "ru.crazerr.cashtracker.core.utils"
}