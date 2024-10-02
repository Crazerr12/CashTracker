import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:transaction:data"))
    implementation(project(":feature:transaction:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.transaction.presentation"
}