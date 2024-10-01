import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
   id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:budget:data"))
    implementation(project(":feature:budget:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.budget.presentation"
}