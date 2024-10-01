import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
   id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:goals:data"))
    implementation(project(":feature:goals:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.goals.presentation"
}