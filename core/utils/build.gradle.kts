import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("kmp.base.plugin")
}

commonMainDependencies {
    implementation(libs.decompose)
    implementation(libs.essenty)
    implementation(libs.decompose.extensions)
}

android {
    namespace = "ru.crazerr.cashtracker.core.utils"
}