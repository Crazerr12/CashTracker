import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.presentation.plugin")
}

commonMainDependencies {
    implementation(project(":feature:account:domain"))
}

android {
    namespace = "ru.crazerr.cashtracker.feature.account.presentation.api"
}