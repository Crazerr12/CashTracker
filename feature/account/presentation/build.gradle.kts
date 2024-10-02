import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:account:data"))
    implementation(project(":feature:account:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.account.presentation"
}