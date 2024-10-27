import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:budget:data"))
    implementation(project(":feature:budget:domain"))
    implementation(project(":feature:budget:presentation:api"))
    implementation(project(":feature:category:presentation:api"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.budget.presentation"
}