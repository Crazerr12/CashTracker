import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.domain.plugin")
}

commonMainDependencies {
    implementation(libs.kotlinx.datetime)
    api(project(":feature:category:domain:api"))
    api(project(":feature:account:domain:api"))
}

android {
    namespace = "ru.crazerr.cashtracker.feature.transaction.domain.api"
}