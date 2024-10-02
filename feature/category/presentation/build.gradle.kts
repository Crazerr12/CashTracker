import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:category:data"))
    implementation(project(":feature:category:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.category.presentation"
}