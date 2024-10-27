import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:goal:data"))
    implementation(project(":feature:goal:domain"))
    implementation(project(":feature:goal:presentation:api"))
    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.goal.presentation"
}