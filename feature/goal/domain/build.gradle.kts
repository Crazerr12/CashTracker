import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.domain.plugin")
}

commonMainDependencies {
    api(project(":feature:goal:domain:api"))
    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.goal.domain"
}