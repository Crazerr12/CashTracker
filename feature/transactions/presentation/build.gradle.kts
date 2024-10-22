import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:transactions:data"))
    implementation(project(":feature:transactions:domain"))
    implementation(project(":feature:transaction:presentation:api"))

    implementation(libs.kotlinx.datetime)
    implementation(libs.paging.compose.common)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.transactions.presentation"
}