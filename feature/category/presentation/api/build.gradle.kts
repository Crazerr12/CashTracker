import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:category:domain"))
}

android {
    namespace = "ru.crazerr.cashtracker.feature.category.presentation.api"
}