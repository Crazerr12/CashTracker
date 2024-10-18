import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:transaction:domain"))
    implementation(project(":feature:currency:domain:api"))
    implementation(project(":feature:category:presentation:api"))
    implementation(project(":feature:account:presentation:api"))
    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.transaction.presentation.api"
}