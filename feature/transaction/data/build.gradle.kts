import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("feature.data.plugin")
}

commonMainDependencies {
    implementation(project(":feature:transaction:domain"))

    implementation(libs.kotlinx.datetime)
}

android {
    namespace = "ru.crazerr.cashtracker.feature.transaction.data"
}