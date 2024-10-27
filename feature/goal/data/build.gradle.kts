import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.data.plugin")
}

commonMainDependencies {
    implementation(project(":feature:goal:domain"))
    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.goal.data"
}