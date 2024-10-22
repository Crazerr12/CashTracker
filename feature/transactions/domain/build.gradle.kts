import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.domain.plugin")
}

commonMainDependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.paging.compose.common)
    api(project(":feature:transaction:domain:api"))
    api(project(":feature:account:domain:api"))
    api(project(":feature:category:domain:api"))
}

android {
    namespace = "ru.crazerr.cashtracker.feature.transactions.domain"
}