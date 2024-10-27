import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.domain.plugin")
}

commonMainDependencies {
    implementation(project(":feature:category:domain:api"))
    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.budget.domain.api"
}