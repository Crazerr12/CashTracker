import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.data.plugin")
}

commonMainDependencies {
    implementation(project(":feature:account:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.account.data"
}